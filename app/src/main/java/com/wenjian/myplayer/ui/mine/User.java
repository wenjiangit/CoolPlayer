package com.wenjian.myplayer.ui.mine;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

/**
 * Description: User
 * Date: 2018/1/31
 *
 * @author jian.wen@ubtrobot.com
 */
@Entity
public class User {

    @NonNull
    @PrimaryKey
    private String id;
    @Nullable
    @ColumnInfo
    private String name;
    @Nullable
    @ColumnInfo()
    private int age;
    @NonNull
    @ColumnInfo
    private int sex;

    @NonNull
    @ColumnInfo
    private double money;

    public User(@NonNull String id, String name, int age, @NonNull int sex, double money) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.money = money;
    }

    @Ignore
    public User(String name, int age, int sex, double money) {
        this(UUID.randomUUID().toString(), name, age, sex, money);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
