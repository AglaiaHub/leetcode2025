package patterns;

import java.util.*;

/*## Задание 4: Система уведомлений магазина

*Задание:* Интернет-магазин хочет уведомлять клиентов о различных событиях: поступлении товара, изменении цены, начале распродажи.

*Требования:*

- Клиенты могут подписываться на уведомления о конкретных товарах
- Система поддерживает разные способы уведомлений (email, SMS, push)
- Клиенты должны иметь возможность отписаться от уведомлений
- При изменении статуса товара все подписчики получают уведомление
- Список подписчиков может динамически изменяться

*Задача:* Как организовать систему, где изменения в одном объекте автоматически отражаются на множестве других?


 */
public class T4 {
    public static void main(String[] args) {
        Subscriber subscriber = new SmsNotificationSubscriber();
        Subscriber subscriber1 = new PushNotificationSubscriber();

        Good1 good1 = new Good1();
        good1.eventManager.subscribe(good1.eventType, subscriber);
        good1.eventManager.subscribe(good1.eventType, subscriber1);

        good1.setStatus("available");

        good1.eventManager.unsubscribe(good1.eventType, subscriber);

        good1.setStatus("unavailable");
        good1.eventManager.notify(good1.eventType, "good will be soon");
    }

    private static class EventManager {
        private Map<String, List<Subscriber>> mapa = new HashMap<>();

        public void subscribe(String eventType, Subscriber listener) {
            if (!mapa.containsKey(eventType)) mapa.put(eventType, new ArrayList<>());
            mapa.get(eventType).add(listener);
        }

        public void unsubscribe(String eventType, Subscriber listener) {
            mapa.get(eventType).remove(listener);
        }

        public void notify(String eventType, String msg) {

            for (Subscriber subscriber : mapa.get(eventType)) {
                subscriber.send(eventType, msg);
            }
        }
    }

    private static class Good1 {
        private EventManager eventManager;
        private String status;
        private String eventType = "Good1";

        public Good1() {
            eventManager = new EventManager();
            status = "<UNK>";
        }

        public void setStatus(String status) {
            this.status = status;
            eventManager.notify(eventType, eventType+" set new status: "+status);
        }

    }

    private static interface Subscriber {
        void send(String eventType, String msg);
    }

    private static class EmailNotificationSubscriber implements Subscriber {

        @Override
        public void send(String eventType, String msg) {
            System.out.println("Email Notification Subscriber: " + msg);
        }
    }

    private static class SmsNotificationSubscriber implements Subscriber {
        @Override
        public void send(String eventType, String msg) {
            System.out.println("Sms Notification Subscriber: " + msg);
        }
    }

    private static class PushNotificationSubscriber implements Subscriber {
        @Override
        public void send(String eventType, String msg) {
            System.out.println("Push Notification Subscriber: " + msg);
        }
    }
}
