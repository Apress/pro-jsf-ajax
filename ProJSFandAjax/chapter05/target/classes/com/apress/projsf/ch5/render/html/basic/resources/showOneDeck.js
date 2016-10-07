/**
 * The onclick handler for HtmlShowOneDeckRenderer.
 *
 * @param formClientId  the clientId of the enclosing UIForm component
 * @param clientId      the clientId of the ProShowOneDeck component
 * @param itemId        the id of the UIShowItem that was clicked
 */
function _showOneDeck_click(
  formClientId, 
  clientId, 
  itemId)
{
  var form = document.forms[formClientId];
  var input = form[clientId];
  if (!input)
  {
    input = document.createElement("input");
    input.type = 'hidden';
    input.name = clientId;
    form.appendChild(input);
  }
  input.value = itemId;
  form.submit();
}