const storageKey = 'items';

function getItems() {
    const storedItems = localStorage.getItem(storageKey);
    return storedItems ? JSON.parse(storedItems) : [];
}

function saveItems(items) {
    localStorage.setItem(storageKey, JSON.stringify(items));
}
function addItem(item) {
    const items = getItems();
    items.push(item);
    saveItems(items);
}
function updateItem(index, newItem) {
    const items = getItems();
    items[index] = newItem;
    saveItems(items);
}
function deleteItem(index) {
    const items = getItems();
    items.splice(index, 1);
    saveItems(items);
}
