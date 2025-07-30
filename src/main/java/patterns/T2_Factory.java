package patterns;

/*Задание 2: Система доставки

*Задание:* Создайте систему управления различными видами транспорта для службы доставки. Компания использует автомобили, велосипеды и самолеты, но планирует добавлять новые виды транспорта.

*Требования:*

- Каждый вид транспорта имеет свой уникальный способ доставки
- Система должна легко расширяться новыми видами транспорта
- Клиентский код не должен знать о конкретных классах транспорта
- Создание объектов транспорта должно происходить в одном месте
- Различные подразделения компании могут использовать разные типы транспорта

*Задача:* Как организовать создание объектов транспорта, чтобы система была гибкой и расширяемой?

     */

public class T2_Factory {
    public static void main(String[] args) {

        LogisticFactory logisticFactory = new LogisticFactory();
        Transport transport = logisticFactory.createTransport(new Car());
        transport.deliver();
    }

    public static class LogisticFactory {
        public Transport createTransport (Transport type){
            Transport transport = null;

            switch (type) {
                case Car ignored -> transport = new Car();
                case Bike ignored -> transport = new Bike();
                case Plane ignored -> transport = new Plane();
                default -> throw new IllegalStateException("Unexpected value: " + type);
            }

            return transport;
        }
    }

    public abstract static class Transport implements Deliverable {

    }

    public static interface Deliverable {
        public void deliver();
    }

    public static class Car extends Transport{
        @Override
        public void deliver() {
            System.out.println("Delivering car");
        }
    }

    public static class Bike extends Transport{
        @Override
        public void deliver() {
            System.out.println("Delivering bike");
        }
    }

    public static class Plane extends Transport{
        @Override
        public void deliver() {
            System.out.println("Delivering plane");
        }
    }


}
