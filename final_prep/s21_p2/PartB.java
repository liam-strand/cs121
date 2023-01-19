package final_prep.s21_p2;

import java.util.*;
import java.lang.reflect.*;

public class PartB {
    public static Object[][] readBoard(List<String> lines) {
        Object[][] out_arr = new Object[8][8];
        
        for (String line : lines) {
            String[] parts = line.split(" ");
            String idx = parts[0];
            String name = parts[1];
            String color = parts[2];
            Color c;
            if (color.equals("w")) {
                 c = Color.WHITE;
            } else if (color.equals("b")) {
                c = Color.BLACK;
            } else {
                throw new RuntimeException("you done goofed");
            }
            
            if (idx.length() != 2) { throw new RuntimeException("oops"); }
            char col = idx.charAt(0);
            char row = idx.charAt(1);
            
            int col_int = col - 'a';
            int row_int = row - '1';
            
            if (col_int < 0 || col_int > 7 || row_int < 0 || row_int > 7) {
                throw new RuntimeException("you done goofed");
            }

            out_arr[col_int][row_int] = instantiatePiece(name, c);
        }

        return out_arr;
    }

    private static Object instantiatePiece(String name, Color c) {
        try {
            return Class.forName(name)
            .getConstructor(Color.class).newInstance(c);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}

enum Color {
    BLACK,
    WHITE
}
