package patterns;

import lombok.*;

/*## Задание 3: Конфигуратор компьютера

*Задание:* Разработайте систему для онлайн-магазина компьютеров. Покупатели должны иметь возможность собирать компьютер из различных комплектующих.

*Требования:*

- Компьютер состоит из обязательных компонентов (процессор, память) и опциональных (видеокарта, SSD)
- Процесс сборки должен быть пошаговым и понятным
- Некоторые комплектующие несовместимы между собой
- Должна быть возможность создавать готовые конфигурации
- Клиент должен видеть промежуточный результат на каждом шаге

*Задача:* Как спроектировать процесс создания сложного объекта с множеством параметров?

 */
public class T3 {
    public static void main(String[] args) {
        Computer computer = new Computer.ComputerBuilder("A", "B")
                .setVideoCard("C")
                .setSsd("D")
                .build();
        System.out.println(computer.toString());
    }


    @Getter
    @Setter
    @ToString
    private static class Computer {
        private String processor;
        private String memory;
        private String videoCard;
        private String ssd;

        private Computer(String ssd, String videoCard, String memory, String processor) {
            this.ssd = ssd;
            this.videoCard = videoCard;
            this.memory = memory;
            this.processor = processor;
        }

        @Setter
        public static class ComputerBuilder {
            private String processor;
            private String memory;
            private String videoCard;
            private String ssd;

            public ComputerBuilder(String processor, String memory) {
                this.processor = processor;
                this.memory = memory;
            }

            public ComputerBuilder setVideoCard(String videoCard) {
                this.videoCard = videoCard;
                return this;
            }

            public ComputerBuilder setSsd(String ssd) {
                this.ssd = ssd;
                return this;
            }

            public Computer build() {
                return new Computer(processor, memory, videoCard, ssd);
            }
        }

    }
}