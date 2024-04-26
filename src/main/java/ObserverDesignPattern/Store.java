package ObserverDesignPattern;

public class Store {
    public static void main(String args[]){
        StockObservable iphoneStockObservable = new IphoneStockObservable();
        NotificationAlertObserver observer1 = new EmailAlertObserverImpl(iphoneStockObservable,"mani@xyz.com");
        NotificationAlertObserver observer2 = new EmailAlertObserverImpl(iphoneStockObservable,"ayu@xyz.com");
        NotificationAlertObserver observer3 = new EmailAlertObserverImpl(iphoneStockObservable,"raj@xyz.com");
        iphoneStockObservable.add(observer1);
        iphoneStockObservable.add(observer2);
        iphoneStockObservable.setStockCount(10);
    }
}
