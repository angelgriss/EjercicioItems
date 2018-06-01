package service;

import model.Item;
import model.ItemException;

import java.util.HashMap;
import java.util.List;

public class ItemServiceHashMap implements ItemService{

    private HashMap<String, Item> itemMap;

    public ItemServiceHashMap() {
        itemMap = new HashMap<>();
    }

    @Override
    public void addItem(Item item) throws ItemException{
        itemMap.put(item.getId(), item);
    }

    @Override
    public Item getItem(String id) throws ItemException {
        return itemMap.get(id);
    }

    public List<Item> getAll() throws ItemException//get
    {
        return null;
    }

    @Override
    public void editUsuario(Item forEdit) throws ItemException{
        Item toEdit = itemMap.get(forEdit.getId());
        toEdit.setCategory_id(forEdit.getCategory_id());
        toEdit.setCondition(forEdit.getCondition());
    }

    @Override
    public void deleteItem(String id) throws ItemException{
        itemMap.remove(id);
    }
}
