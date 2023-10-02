package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    private static final String PICTURE = "picture.png";
    private static final String MEM_FILE_FORMAT = "png";
    private static final String MEM_PICTURE = "mem-picture.png";
    private static final String HELP = "help";
    private static final String MEM = "mem";
    private static final String defaultFontLogical = "Dialog";
    private static final int defaultSize = 30;
    private static final int defaultFont = Font.PLAIN;
    private static final Color defaultColor = Color.BLACK;
    private static final String WELCOME = """
            help - команда вывода на экран мануала по использованию программы
            Пример команды для вывода мануала на экран: "java -jar TestTask.jar help"
            """;
    private static final String MANUAL = """
            mem - команда для добавления текста к картинке.
            Формат ввода команды с различными параметрами:
            ----------------------------------------------------------------------------------------
            java -jar TestTask.jar mem картинка "текст" положение цвет шрифт стиль размер сохранение
            ----------------------------------------------------------------------------------------
            - картинка - имя файла картинки, к которой будет добавлен текст (с расширением)
            - "текст" - текст, который будет добавлен к картинке (текст должен быть взят в кавычки)
            - положение - указание, в какую часть картинки будет добавлен текст. Возможны три
              варианта расположения: TOP, CENTER, BOTTOM.
            - цвет - цвет текста. возможные варианты: BLACK, BLUE, CYAN, DARK_GRAY, GRAY, GREEN,
              LIGHT_GRAY, MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW.
            - шрифт - возможные варианты: Dialog, Dialoginput, Serif, Sansserif, Monospaced.
            - стиль - BOLD(полужирный), ITALIC(курсив), PLAIN(обычный)
            - размер - размер шрифта
            - сохранение - по умолчанию программа вносит изменения в оригинальный файл картинки.
              используйте аргумент SAVE, что бы сохранить оригинальный файл, в таком случае будет
              создан новый файл (или перезаписан существующий) в текущем каталоге с именем "mem-picture".
            Пример команды:
            java -jar TestTask.jar mem picture.png "Hello world!" CENTER RED Serif BOLD 32 SAVE
            """;


    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case HELP -> System.out.println(MANUAL);
                case MEM -> createMem(args);
            }
        } else System.out.println(WELCOME);
    }

    private static void createMem(String[] args) {
        if (args.length >= 3) {
            try {
                BufferedImage image = ImageIO.read(new File(PICTURE));
                Graphics g = image.getGraphics();
                g.setColor(selectColor(args));
                g.setFont(selectFont(args));
                StartPosition startPosition = selectPosition(args, image);
                g.drawString(args[2], startPosition.getIndentWidth(), startPosition.getIndentHeight());
                saveMem(args, image);

            } catch (Exception e) {
                System.out.println("Произошла ошибка, неверно введены параметры");
            }
        }
    }

    private static void saveMem(String[] args, BufferedImage image) {
        if (args.length > 8) {
            if (args[8].equalsIgnoreCase("SAVE")) {
                try {
                    ImageIO.write(image, MEM_FILE_FORMAT, new File(MEM_PICTURE));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            try {
                ImageIO.write(image, MEM_FILE_FORMAT, new File(PICTURE));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Font selectFont(String[] args) {
        Font font;
        try {
            return new Font(args[5], selectStyle(args), selectSize(args));
        } catch (Exception e) {
            System.out.println("Ошибка при определении логического шрифта, использовано значение по умолчанию");
            return new Font(defaultFontLogical, selectStyle(args), selectSize(args));
        }

    }

    private static StartPosition selectPosition(String[] args, BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        int indentWidth = width / 10;
        int indentHeight = height / 10;
        try {
            switch (args[3]) {
                case "TOP" -> {
                    indentHeight = (int) (height * 0.1);
                }
                case "CENTER" -> {
                    indentHeight = (int) (height * 0.5);
                }
                case "BOTTOM" -> {
                    indentHeight = (int) (height * 0.9);
                }
            }
            return new StartPosition(indentHeight, indentWidth);
        } catch (Exception e) {
            System.out.println("Ошибка при определении положения текста. Использовано значение по умолчанию");
            return new StartPosition(indentHeight, indentWidth);
        }

    }


    private static int selectSize(String[] args) {
        try {
            return Integer.parseInt(args[7]);
        } catch (Exception e) {
            System.out.println("Ошибка при определении размера шрифта, использовано значение по умолчанию");
            return defaultSize;
        }
    }

    private static int selectStyle(String[] args) {
        try {
            switch (args[6]) {
                case "BOLD" -> {
                    return Font.BOLD;
                }
                case "ITALIC" -> {
                    return Font.ITALIC;
                }
                default -> {
                    return Font.PLAIN;
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при выборе стиля, использовано значение по умолчанию");
            return defaultFont;
        }
    }

    private static Color selectColor(String[] args) {
        try {
            switch (args[4]) {
                case "BLUE" -> {
                    return Color.BLUE;
                }
                case "CYAN" -> {
                    return Color.CYAN;
                }
                case "DARK_GRAY" -> {
                    return Color.DARK_GRAY;
                }
                case "GRAY" -> {
                    return Color.GRAY;
                }
                case "GREEN" -> {
                    return Color.GREEN;
                }
                case "LIGHT_GRAY" -> {
                    return Color.LIGHT_GRAY;
                }
                case "MAGENTA" -> {
                    return Color.MAGENTA;
                }
                case "ORANGE" -> {
                    return Color.ORANGE;
                }
                case "PINK" -> {
                    return Color.PINK;
                }
                case "RED" -> {
                    return Color.RED;
                }
                case "WHITE" -> {
                    return Color.WHITE;
                }
                case "YELLOW" -> {
                    return Color.YELLOW;
                }
                default -> {
                    return Color.BLACK;
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при выборе цвета, использовано значение по умолчанию");
            return defaultColor;
        }
    }
}