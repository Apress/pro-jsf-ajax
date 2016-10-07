/**
 * Populates the select element with a list of books in a specific book category.
 *
 * @param serviceURL  the service URL for retrieving JSON data files
 * @param selectId    the id of the target select element to populate
 * @param category    the book category for the populated books
 */
function populateBookList(
  serviceURL,
  selectId,
  category) 
{
  var xmlhttp = createHttpRequest();
  // You can use any type of data source, but for the sample
  // we are going to use a simple JSON file that contains our data.
  var requestURL = serviceURL + '/booklist-' + category.toLowerCase() + '.json';
  xmlhttp.open("GET", requestURL);
  xmlhttp.onreadystatechange = function()
    {
      if (xmlhttp.readyState == 4) 
      {
        transferListItems(selectId, eval(xmlhttp.responseText));
      }
    };
  xmlhttp.send(null);
}

/**
 * Transfers the list items from the JSON array 
 * to options in the select element.
 *
 * @param selectId    the id of the target select element to populate
 * @param listArray   the retrieved list of books
 */
function transferListItems(
  selectId,
  listArray) 
{
  var select = document.getElementById(selectId);

  // reset the select options
  select.length = 0;
  select.options[0] = new Option('[select]');

  // transfer the book list items
  for(var i=0; i < listArray.length; i++) 
  {
    // create the new Option
    var option = new Option(listArray[i]);
    // add the Option onto the end of the select options list
    select.options[select.length] = option;
  }
}

/**
 * Creates a new XMLHttpRequest object.
 */
function createHttpRequest()
{
  var xmlhttp = null;

  if (window.ActiveXObject)
  {
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
  }
  else if (window.XMLHttpRequest)
  {
    xmlhttp = new XMLHttpRequest();
  }
  
  return xmlhttp;
}
