package com.company;

import java.util.Scanner;

public class Main {

    static MyArrayList<Polinomial> global = new MyArrayList<>();
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        MyArrayList<Polinomial> q = new MyArrayList<>();
        MyArrayList<Polinomial> p = new MyArrayList<>();
        MyArrayList<Polinomial> r = new MyArrayList<>();
        //заполнение списка с клавы
        input(q);
        input(p);
        //сортировка списка по степени
        sort(q);
        sort(p);
        calc(p);
        calc(q);
        System.out.print(ANSI_GREEN + "Уравнение p: " + ANSI_RESET);
        outputFunc(q);
        System.out.print(ANSI_GREEN + "Уравнение q: " + ANSI_RESET);
        outputFunc(p);
        System.out.println(ANSI_GREEN + "РЕЗУЛЬТАТ ФУНКЦИИ EQUALITY: " + ANSI_RESET);
        resOfEquality(Equality(p, q));
        System.out.println(ANSI_GREEN + "РЕЗУЛЬТАТ ФУНКЦИИ MEANING: " + ANSI_RESET);
        Meaning(p, 10);
        System.out.println(ANSI_GREEN + "РЕЗУЛЬТАТ ФУНКЦИИ ADD: " + ANSI_RESET);
        Add(r, p, q);

    }

    private static void outputFunc(MyArrayList<Polinomial> q) {
        for (int a = 0; a < q.size(); a++) {
            if (a != q.size() - 1) System.out.print(q.get(a).toString() + " + ");
            else System.out.print(q.get(a).toString());
        }
        System.out.println();
    }

    private static boolean Equality(MyArrayList<Polinomial> p, MyArrayList<Polinomial> q) {
        boolean check = true;
        if (p.size() == q.size()) {
            for (int a = 0; a < p.size(); a++) {
                if (p.get(a).getPower() != q.get(a).getPower()) {
                    check = false;
                    break;
                } else if (p.get(a).getRate() != q.get(a).getRate()) {
                    check = false;
                    break;
                }
            }
        } else check = false;
        return check;
    }

    private static void resOfEquality(boolean res) {
        if (!res) System.out.println("Многочлены p и q не равны между собой.");
        else System.out.println("Многочлены равны! Поздравляем! Ваш приз - автомобиль!");
    }

    private static void Meaning(MyArrayList<Polinomial> p, int x) {
        int y = 0;
        for (int a = 0; a < p.size(); a++) {
            y += p.get(a).getRate() * Math.pow(x, p.get(a).getPower());
        }
        System.out.println("Значение многочлена в целочисленной точке x: " + y);
    }

    private static void Add(MyArrayList<Polinomial> res, MyArrayList<Polinomial> first, MyArrayList<Polinomial> second) {
        catching(first, second);
        calc(global);
        res = global;
        outputFunc(res);
    }

    private static void input(MyArrayList<Polinomial> list) {
        int i = 1;
        boolean check = true;
        while (check) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Введите cтепень " + i + "-го многочлена функции:");
            int x = scan.nextInt();
            if (x == 0) check = false;
            list.add(new Polinomial(x, 0));
            System.out.println("Введите коэффициент " + i + "-го многочлена функции:");
            int y = scan.nextInt();
            if (y == 0) {
                list.removeAt(i - 1);
                break;
            }
            list.get(i - 1).setRate(y);
            i++;
        }
    }


    private static void sort(MyArrayList<Polinomial> list) {
        for (int i = 0; i < list.size(); i++) {
            int powerx = list.get(i).getPower();
            int ratex = list.get(i).getRate();
            int map = i - 1;
            for (; map >= 0; map--) {
                if (powerx > list.get(map).getPower()) {
                    list.get(map + 1).setPower(list.get(map).getPower());
                    list.get(map + 1).setRate(list.get(map).getRate());
                } else {
                    break;
                }
            }
            list.get(map + 1).setPower(powerx);
            list.get(map + 1).setRate(ratex);
        }
    }

    private static void catching(MyArrayList<Polinomial> first, MyArrayList<Polinomial> second) {
        boolean check = true;
        for (int i = 0; i < first.size(); i++) {
            for (int y = 0; y < second.size(); y++) {
                if (first.get(i).getPower() == second.get(y).getPower()) {
                    global.add(new Polinomial(first.get(i).getPower(), first.get(i).getRate() + second.get(y).getRate()));
                    first.removeAt(i);
                    second.removeAt(y);
                    check = false;
                    break;
                }
            }
            if (!check) break;
        }
        for (int a = 0; a < first.size(); a++) {
            global.add(new Polinomial(first.get(a).getPower(), first.get(a).getRate()));
        }
        for (int b = 0; b < second.size(); b++) {
            global.add(new Polinomial(second.get(b).getPower(), second.get(b).getRate()));
        }
        sort(global);
    }

    private static void calc(MyArrayList<Polinomial> x) {
        for (int i = 0; i < x.size(); i++) {
            for (int y = i + 1; y < x.size(); y++) {
                if (x.get(i).getPower() == x.get(y).getPower()) {
                    x.get(i).setRate(x.get(i).getRate() + x.get(y).getRate());
                    x.removeAt(y);
                    if (x.get(i).getRate() == 0) x.removeAt(i);
                    break;
                }
            }
        }
    }
}
