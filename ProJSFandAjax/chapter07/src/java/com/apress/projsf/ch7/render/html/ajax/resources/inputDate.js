/**
 * Creates a new HtmlInputDate object.
 *
 * @class HtmlInputDate is a proxy object responsible for managing
 *        the popup calendar and syncing the selected value with
 *        an input field.
 *
 * @constructor
 * @param inputId    the id of the input
 * @param pattern    the date format pattern
 * @param targetURL  the targetURL to check availability
 */
function HtmlInputDate(
  inputId,
  pattern,
  targetURL)
{
  this._input = document.getElementById(inputId);
  this._pattern = pattern;
  this._targetURL = targetURL;
  this._currentMonth = null;
  this._currentYear = null;

  var tableNode = document.getElementById(inputId + '$calendar');
  if (tableNode == null)
  {
    // used by onclick event handlers
    var self = this;

    // <table class="ProInputDateCalendar" 
    //        cellspacing="0px"
    //        cellpadding="0px" >
    var tableNode = document.createElement('table');
    tableNode.appendChild(document.createElement('tbody'));
    
    tableNode.id = inputId + '$calendar';
    tableNode.className = 'ProInputDateCalendar';
    tableNode.cellSpacing = '0px';
    tableNode.cellPadding = '0px';
    tableNode.style.position = 'absolute';
    tableNode.style.visibility = 'hidden';
    
    var input = this._input;
    tableNode.style.top = input.offsetTop + input.offsetHeight;
    tableNode.style.left = input.offsetLeft;

    // <thead>
    //   <tr class="toolbar">
    //     <td class="control" 
    //         onclick="this.scroll(-1)" >&lt;</td>
    //     <td colspan="5" ></td>
    //     <td class="control" 
    //         onclick="this.scroll(1)">&gt;</td>
    //   </tr>
    //   <tr class="headings" >
    //     <td>Sun</td>
    //     <td>Mon</td>
    //     <td>Tue</td>
    //     <td>Wed</td>
    //     <td>Thu</td>
    //     <td>Fri</td>
    //     <td>Sat</td>
    //   </tr>
    // </thead>
    var tHeadNode = tableNode.createTHead();
    
    var trToolbarNode = tHeadNode.insertRow(-1);
    trToolbarNode.className = 'toolbar';
    
    var tdLeftNode = trToolbarNode.insertCell(-1);
    tdLeftNode.appendChild(document.createTextNode('<'));
    tdLeftNode.onclick = function(event) { self._scroll(-1); };
  
    var tdTitleNode = trToolbarNode.insertCell(-1);
    tdTitleNode.colSpan = 5;
    tdTitleNode.appendChild(document.createTextNode(' '));
   
    var tdRightNode = trToolbarNode.insertCell(-1);
    tdRightNode.appendChild(document.createTextNode('>'));
    tdRightNode.onclick = function(event) { self._scroll(1); };
  
    var trHeadingsNode = tHeadNode.insertRow(-1);
    trHeadingsNode.className = 'headings';
    var dayNames = HtmlInputDate._DAY_NAMES;
    for (var i=0; i < dayNames.length; i++)
    {
      var tdNode = trHeadingsNode.insertCell(-1);
      tdNode.appendChild(document.createTextNode(dayNames[i]));
    }

    // <tbody onclick="this._clickCell(event)" >
    //  <tr>
    //    <td></td> * 7 days
    //  </tr> * 6 weeks
    // </tbody>
    var tBodyNode = tableNode.tBodies[0];
    tBodyNode.onclick = function (event) { self._clickCell(window.event || event); };
    for (var week=0; week < 6; week++)
    {
      var trNode = tBodyNode.insertRow(-1);
      for (var day=0; day < 7; day++)
      {
        var tdNode = trNode.insertCell(-1);
        tdNode.appendChild(document.createTextNode(' '));
      }
    }
    
    document.body.appendChild(tableNode);
  }
  
  this._tableNode = tableNode;
  this._titleNode = tableNode.tHead.rows[0].cells[1].childNodes[0];
}

/**
 * Shows the popup calendar.
 */
HtmlInputDate.prototype.showPopup = function()
{
  var tableNode = this._tableNode;
  
  if (tableNode.style.visibility == 'hidden')
  {
    // read the user-defined date string from the input field
    var dateString = this._input.value;
    // parse that date string into a Date object
    var parsedDate = this._parseDate(dateString, this._pattern);
    // if parse was successful use the Date, 
    // otherwise use today's Date
    var activeDate = (parsedDate != null) ? parsedDate : new Date();

    // ensure no selection initially
    this._deselect();
    
    // specify month and year to be displayed
    var month = activeDate.getMonth();
    var year = activeDate.getFullYear();
    this._currentMonth = month;
    this._currentYear = year;
    
    // scroll zero months to ensure fully 
    // populated calendar day cells
    this._scroll(0);
    
    // select initial date (if possible)
    if (parsedDate)
      this._select(parsedDate.getDate());
  }
  else
  {
    // dismiss the calendar popup
    this._hidePopup();
  }
}
/**
 * Hides the popup calendar.
 * @private
 */
HtmlInputDate.prototype._hidePopup = function()
{
  this._tableNode.style.visibility = 'hidden';
}
/**
 * Selects the cell when it is clicked.
 *
 * @param event  the click event
 * @private
 */
HtmlInputDate.prototype._clickCell = function(event)
{
  var cellNode = (event.target || event.srcElement);
  var rowNode = cellNode.parentNode;
  
  var row = rowNode.sectionRowIndex;
  var col = cellNode.cellIndex;
  var day = Number(cellNode.firstChild.nodeValue);
  
  // detect other month cells
  if (row == 0 && day > 7)
  {
    this._scroll(-1);
  }
  else if (row > 3 && day < 15)
  {
    this._scroll(1);
  }
  else
  {
    if (this._isAvailable(day))
    {
      // format the date in the attached input element
      var selectedDate = this._calculateDate(day);
      this._input.value = this._formatDate(selectedDate, this._pattern);
      
      // dismiss the popup calendar
      this._hidePopup();
    }
  }
}
/**
 * Determines if day is available for selection.
 *
 * @param day        the day to check
 * @private
 */
HtmlInputDate.prototype._isAvailable = function(day)
{
  var dayCell = this._findCell(day);

  // class="noselect today" is possible
  if (dayCell.className.substring(0, 'noselect'.length) == 'noselect')
    return false;

  return true;
}
/**
 * Visually update the selected day cell.
 *
 * @param day        the day to select
 * @private
 */
HtmlInputDate.prototype._select = function(day)
{
  // first, deselect the currently selected dayCell
  this._deselect();
      
  // now, select the dayCell that was clicked
  this._selectedDate = this._calculateDate(day);
    
  var dayCell = this._findCell(day);
  dayCell.className = 'selected';
}
/**
 * Visually update the selected day cell, if available.
 * @private
 */
HtmlInputDate.prototype._deselect = function()
{
  if (this._selectedDate != null)
  {
    var selectedDay = this._selectedDate.getDate();
    var selectedCell = this._findCell(selectedDay);
    var millisPerDay = 1000 * 60 * 60 * 24;
    var isToday = Math.abs(new Date() - selectedDate - millisPerDay) < millisPerDay/2;
    selectedCell.className = isToday ? 'today' : '';

    // clear out the selection
    this._selectedDate = null;
  }
}
/**
 * Find the day cell for a given day in the calendar.
 *
 * @param day  the day to find
 * @private
 */
HtmlInputDate.prototype._findCell = function(day)
{
  var dayStart = this._calculateDate(1).getDay();
  var dayIndex = (day + dayStart - 1);
  var rowIndex = Math.floor(dayIndex / 7);
  var cellIndex = dayIndex % 7;
  return this._tableNode.tBodies[0].rows[rowIndex].cells[cellIndex];
}
/**
 * Calculates the Date for the specified day, within 
 * the currently displayed month and year.
 *
 * @param day  the day to calculate
 * @private
 */
HtmlInputDate.prototype._calculateDate = function(day)
{
  return new Date(this._currentYear, this._currentMonth, day);
}
/**
 * Scrolls the visible month by +/- offset months.
 *
 * @param offset  the number of months to scroll
 * @private
 */
HtmlInputDate.prototype._scroll = function(offset)
{
  // scroll months, updating year as necessary
  this._currentMonth = this._currentMonth + offset;
  this._currentYear += Math.floor(this._currentMonth / 12);
  this._currentMonth = (this._currentMonth + 12) % 12;
  
  // use Mabon to retrieve availability
  if (this._targetURL)
  {
    var startDate = this._calculateDate(1);
    var endDate = this._calculateDate(31);

    var millisPerDay = 1000 * 60 * 60 * 24;
    var startDay = Math.floor(startDate.getTime() / millisPerDay);
    var endDay = Math.floor(endDate.getTime() / millisPerDay);

    // use Mabon to determine availability
    var self = this;
    net.java.dev.mabon.send(
      { 
        url: this._targetURL, 
        args: [startDay, endDay], 
        callback: function(result) { self._display(result); }
      });
  }
  else
  {
    var available = [];
    for (var i=0; i < 32; i++)
    {
      available.push(true);
    }
    this._display(available);
  }
}
/**
 * Displays the calendar by populating the day cells.
 *
 * @param available  the day availability as a boolean array
 * @private
 */
HtmlInputDate.prototype._display = function(available)
{
  var date = this._calculateDate(1);
  var month = date.getMonth();
  var year  = date.getFullYear();
  var dayStart = date.getDay();

  // update title    
  this._titleNode.nodeValue = this._formatDate(date, 'MMM yyyy');
  
  var millisPerDay = 1000 * 60 * 60 * 24;
  var dateStart = new Date(date.getTime() - dayStart * millisPerDay);
  var dateEnd = new Date(date.getTime() + 32 * millisPerDay);
  dateEnd.setDate(0);

  var selectedDate = this._selectedDate;
  var selectedDay = this._isVisible(selectedDate) ? selectedDate.getDate() 
                                                  : null;

  var currentDate = new Date();
  var currentDay = this._isVisible(currentDate) ? currentDate.getDate() 
                                                : null;

  var tBody = this._tableNode.tBodies[0];
  for (var rowIndex=0, dayIndex=0; rowIndex < 6; rowIndex++)
  {
    var rowNode = tBody.rows[rowIndex];
    for (var cellIndex=0; cellIndex < 7; cellIndex++, dayIndex++)
    {
      var cellNode = rowNode.cells[cellIndex];
      var day = dayIndex - dayStart + 1;
      if (day <= 0)
      {
        day = dateStart.getDate() + dayIndex;
        cellNode.className = 'other'
      }
      else if (day > dateEnd.getDate())
      {
        day = day - dateEnd.getDate();
        cellNode.className = 'other'
      }
      else
      {
        var canSelect = available[day - 1];
        var classNames = [];

        if (canSelect)
        {
          if (day == selectedDay)
            classNames.push('selected');
        }
        else
        {
          classNames.push('noselect');
        }

        if (day == currentDay)
          classNames.push('today');

        cellNode.className = classNames.join(' ');
      }
      
      if (!cellNode.firstChild)
        cellNode.appendChild(document.createTextNode(String(day)));
      else
        cellNode.firstChild.nodeValue = String(day);
    }
  }

  // display the popup;
  this._tableNode.style.visibility = 'visible';
}

/**
 * Determines if the specified date is currently
 * visible in the popup calendar.
 * @private
 */
HtmlInputDate.prototype._isVisible = function(
  date)
{
  return (date != null &&
          date.getMonth() == this._currentMonth &&
          date.getFullYear() == this._currentYear); 
}

/**
 * Parses the formatted date string to a date object.
 * @private
 */
HtmlInputDate.prototype._parseDate = function(
  dateString,
  pattern)
{
  if (pattern == "d MMMMM yyyy")
  {
    var dmy = dateString.split(" ");
    var day = Number(dmy[0]);
    var month = -1;
    var year = Number(dmy[2]);
    var monthNames = HtmlInputDate._MONTH_NAMES;

    for (var i=0; i < monthNames.length; i++)
    {
      if (monthNames[i] == dmy[1])
        month = i;
    }

    if (month != -1 && !isNaN(year) && !isNaN(month) && !isNaN(day))
      return new Date(year, month, day);
  }

  return null;
}

/**
 * Formats the date as a string.
 * @private
 */
HtmlInputDate.prototype._formatDate = function(
  date,
  pattern)
{
  var monthNames = HtmlInputDate._MONTH_NAMES;

  if (pattern == "d MMMMM yyyy")
    return date.getDate() + ' ' + monthNames[date.getMonth()] + ' ' + date.getFullYear();
  
  return monthNames[date.getMonth()] + ' ' + date.getFullYear();
}

/**
 * The day names for a week.
 * @private
 */
HtmlInputDate._DAY_NAMES = ['Sun', 'Mon', 'Tue', 
                            'Wed', 'Thu', 'Fri', 'Sat'];

/**
 * The month names for a year.
 * @private
 */
HtmlInputDate._MONTH_NAMES = ['January', 'February', 'March',  
                              'April', 'May', 'June', 
                              'July', 'August', 'September', 
                              'October', 'November', 'December'];
