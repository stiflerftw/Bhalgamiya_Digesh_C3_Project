import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<Item> selectedItemList = new ArrayList<Item>();
    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        return (getCurrentTime().isAfter(openingTime) && getCurrentTime().isBefore(closingTime));
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }
    public String getLocation() {return location; }

    // selectItemByName() -> Method will add menu items input by customer into ArrayList "selectedItemList",
    //  in case no such item exist in menu then it throws exception
    public void selectItemByName(String itemName) throws itemNotFoundException {
        Item selectedItem = findItemByName(itemName);
        if (selectedItem == null)
            throw new itemNotFoundException(itemName);

        selectedItemList.add(selectedItem);
    }

    // getselectedItemList() -> Method will return list (selectedItemList) of selected menu items by customer
    public List<Item> getselectedItemList() { return selectedItemList; }

    // returnOrderValue() -> Method will return cost of order by customer
    public int returnOrderValue(List<Item> itemList) {
        int totalCost = 0;
        for(Item value : itemList) {
            totalCost = totalCost + value.getPrice();
        }
        return totalCost;
    }

}
