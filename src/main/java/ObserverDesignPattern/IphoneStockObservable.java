package ObserverDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class IphoneStockObservable implements  StockObservable{
    List<NotificationAlertObserver> observerList = new ArrayList<>();
    public int stock = 0;
    @Override
    public void add(NotificationAlertObserver observer) {
       observerList.add(observer);
    }

    @Override
    public void remove(NotificationAlertObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for(NotificationAlertObserver obs : observerList){
            obs.update();
        }
    }

    @Override
    public void setStockCount(int newStock) {
        if(stock == 0){
            notifySubscribers();
        }
        stock+=newStock;
    }

    @Override
    public int getStockCount() {
        return stock;
    }
}
