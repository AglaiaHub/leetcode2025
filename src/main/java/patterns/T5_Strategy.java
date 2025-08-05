package patterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*Задание 5: Система расчета доставки

*Задание:* Логистическая компания предлагает несколько видов доставки с разными алгоритмами расчета стоимости. Планируется добавление новых тарифов.

*Требования:*

- Стандартная доставка: базовая стоимость + коэффициент за расстояние
- Экспресс доставка: удвоенная стоимость + фиксированная надбавка
- Премиум доставка: сложная формула с учетом веса, расстояния и времени
- Возможность переключения между тарифами во время работы
- Легкое добавление новых тарифных планов

*Задача:* Как организовать систему, чтобы алгоритм расчета можно было выбирать и изменять независимо от основного кода?

 */
public class T5_Strategy {
    public static void main(String[] args) throws IOException {

        Strategy strategy = new ExpressStrategy();
        Order order = new Order();


        order.processOrder(strategy, 300, 10);

    }

    public static interface Strategy {
        int BASIC = 3000;
        int COEFFICENT = 3;
        int FIX_ADD = 5;
        public int countPrice(int distance, int weight);

    }

    public static class StandartStrategy implements Strategy {
        int distance;
        @Override
        public int countPrice(int distance, int weight) {
            int price = BASIC*2 + distance*COEFFICENT;
            System.out.println("Strategy price is standard: " + price);
            return price;
        }
    }
    public static class ExpressStrategy implements Strategy {
        @Override
        public int countPrice(int distance, int weight) {
            int price = BASIC + distance*COEFFICENT + FIX_ADD;
            System.out.println("Strategy price is Express: " + price);
            return price;
        }
    }
    public static class PremiumStrategy implements Strategy {
        @Override
        public int countPrice(int distance, int weight) {
            int price = BASIC + distance*COEFFICENT + weight*COEFFICENT;
            System.out.println("Strategy price is Premium: " + price);
            return price;
        }
    }

    private static class Order {

        public void processOrder(Strategy strategy, int distance, int weight) {
            int cost = strategy.countPrice(distance, weight);
            System.out.println("Total cost = " + cost);
        }


    }
}
