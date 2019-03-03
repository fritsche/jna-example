/*
 * Copyright (C) 2019 Gian Fritsche <gmfritsche at inf.ufpr.br>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.ufpr.inf.cbio.jnaexample;

import com.sun.jna.Library;
import com.sun.jna.Native;
import java.io.File;
import java.util.Arrays;

/**
 *
 * @author Gian Fritsche <gmfritsche at inf.ufpr.br>
 */
public class Main {

    public interface ExampleJNA extends Library {

        public int sum(int num1, int num2);

        public void sumarray(int a[], int b[], int result[], int size);

    }

    public static void main(String[] args) {
        // get lib folder from resource
        File file = new File(Main.class.getClassLoader().getResource("lib").getFile());
        // set jna.library.path to the path of lib
        System.setProperty("jna.library.path", file.getAbsolutePath());
        // load libeaxmple.so from lib
        ExampleJNA example = (ExampleJNA) Native.load("example", ExampleJNA.class);

        // call sum method from example library
        int a = 2, b = 3;
        int result = example.sum(a, b);
        System.out.println(a + " + " + b + " = " + result);

        // example using arrays
        int size = 5;
        // input 2 integer arrays
        int A[] = new int[]{1, 2, 3, 4, 5};
        int B[] = new int[]{5, 4, 3, 2, 1};
        // output the sum of the arrays
        int R[] = new int[size];
        example.sumarray(A, B, R, size);
        System.out.println("A = " + Arrays.toString(A));
        System.out.println("B = " + Arrays.toString(B));
        System.out.println("A + B = " + Arrays.toString(R));

    }
}
