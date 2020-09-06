package com.company;

import java.util.Scanner;

public class Main {

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
        for(int a = 0; a < q.size(); a++){
            System.out.println(q.get(a).toString());
        }
        for(int b = 0; b < p.size(); b++){
            System.out.println(p.get(b).toString());
        }
        resOfEquality(Equality(p, q));
        Meaning(p, 10);
        Add(p, q, r);

    }

    private static boolean Equality(MyArrayList<Polinomial> p, MyArrayList<Polinomial> q) {
        boolean check = true;
        if(p.size() == q.size()){
            for(int a = 0; a < p.size(); a++){
                if(p.get(a).getRate() != q.get(a).getRate()){
                    check = false;
                    break;
                } else if(p.get(a).getPower() != q.get(a).getPower()) {
                    check = false;
                    break;
                }
            }
        }
        return check;
    }

    private static void resOfEquality(boolean res){
        if(!res) System.out.println("Многочлены p и q не равны между собой.");
        else System.out.println("Многочлены равны! Поздравляем! Ваш приз - автомобиль!");
    }

    private static void Meaning(MyArrayList<Polinomial> p, int x) {
        int y = 0;
        for(int a = 0; a < p.size(); a++){
            y += p.get(a).getRate() * Math.pow(x, p.get(a).getPower());
        }
        System.out.println("Значение многочлена в целочисленной точке x равно " + y);
    }

    private static void Add(MyArrayList<Polinomial> res, MyArrayList<Polinomial> first, MyArrayList<Polinomial> second) {
        if(Equality(first, second)){
            for(int a = 0; a < first.size(); a++){
                res.add(new Polinomial(first.get(a).getPower() + second.get(a).getPower(), first.get(a).getRate() + second.get(a).getRate()));
            }
            for(int b = 0; b < res.size(); b++){
                System.out.println(res.get(b).toString());
            }
        }
    }

    private static void input(MyArrayList<Polinomial> list) {
        int i = 1;
        boolean check = true;
        while(check){
            Scanner scan = new Scanner(System.in);
            System.out.println("Введите cтепень " + i + "-го многочлена функции:");
            int x = scan.nextInt();
            if(x != 0){
                list.add(new Polinomial(x, 0));
            } else check = false;
            System.out.println("Введите коэффициент " + i + "-го многочлена функции:");
            int y = scan.nextInt();
            if(y != 0){
                list.get(i - 1).setRate(y);
            } else check = false;
            i++;
        }
    }


    private static void sort(MyArrayList<Polinomial> list) {
        for(int i = 0; i < list.size(); i ++){
            int powerx = list.get(i).getPower();
            int ratex = list.get(i).getRate();
            int map = i - 1;
            for(; map >= 0; map--) {
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
}
