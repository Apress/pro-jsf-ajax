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
  var form = document.getElementById(formClientId);
  var content = new Object();
  content[clientId] = itemId;
  d2.submit(form, content);
}