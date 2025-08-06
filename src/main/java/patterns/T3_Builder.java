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
public class T3_Builder {
    public static void main(String[] args) {
        Computer computer = new Computer.ComputerBuilder()
                .setProcessor("A")
                .setMemory("B")
                .setVideoCard("C")
                .setSsd("E")
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

//        @Setter
        public static class ComputerBuilder {
            private String processor;
            private String memory;
            private String videoCard;
            private String ssd;

            public ComputerBuilder setProcessor(String processor) {
                this.processor = processor;
                return this;
            }

            public ComputerBuilder setMemory(String memory) {
                this.memory = memory;
                return this;
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
                if (processor == null || memory == null) {
                    throw new NullPointerException("processor and memory cannot be null");
                } else if (videoCard.equals("C") && ssd.equals("D")) {
                    throw new IllegalArgumentException("this SSDs cannot be together with this processor");
                } else {
                    Computer computer = new Computer();
                    computer.setProcessor(processor);
                    computer.setMemory(memory);
                    computer.setVideoCard(videoCard);
                    computer.setSsd(ssd);
                    return computer;
                }


            }
        }

    }
}