package service;

import model.Item;
import model.ItemException;

import java.util.List;

public interface ItemService {

    public void addItem(Item item) throws ItemException;//post
    public Item getItem(String id) throws ItemException;//get
    public List<Item> getAll() throws ItemException;//get
    public void editUsuario(Item item) throws ItemException;//put;
    public void deleteItem(String id) throws ItemException;//delete
}
