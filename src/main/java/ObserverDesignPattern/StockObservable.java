package ObserverDesignPattern;

//Observer Design pattern is used when one system is used making any changes and the consumers
//subscribed to it gets updated of the notification, we make use of Interfaces to achieve this.

public interface StockObservable {
    public void add(NotificationAlertObserver observer);
    public void remove(NotificationAlertObserver observer);
    public void notifySubscribers();
    public void setStockCount(int newStock);
    public int getStockCount();


}
