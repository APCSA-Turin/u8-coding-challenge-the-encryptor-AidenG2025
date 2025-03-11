package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows)
    {
        if (messageLen == 0) 
        {
            return 1;
        }
        return (messageLen + rows - 1) / rows;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) 
    {
        int cols = determineColumns(message.length(), rows);
        String[][] grid = new String[rows][cols];
        
        int index = 0;
        for (int r = 0; r < rows; r++) 
        {
            for (int c = 0; c < cols; c++) 
            {
                if (index < message.length()) 
                {
                    grid[r][c] = message.substring(index, index + 1);
                    index++;
                } 
                else 
                {
                    grid[r][c] = "=";
                }
            }
        }
        
        return grid;
    }

    public static String encryptMessage(String message, int rows)
    {
        if (message.isEmpty()) return "";
        String[][] grid = generateEncryptArray(message, rows);
        int cols = grid[0].length;
        
        String encrypted = "";
        for (int c = cols - 1; c >= 0; c--) 
        {
            for (int r = 0; r < rows; r++) 
            {
                encrypted += grid[r][c];
            }
        }
        
        return encrypted;
    }

    public static String decryptMessage(String encryptedMessage, int rows) 
    {
        if (encryptedMessage.isEmpty()) return "";
        int messageLen = encryptedMessage.length();
        int cols;
        
        if (messageLen % rows == 0) 
        {
            cols = messageLen / rows;
        } 
        else 
        {
            cols = messageLen / rows + 1;
        }
        
        if (rows * cols != messageLen) {
            return "";
        }
        
        String[][] grid = new String[rows][cols];
        
        int index = 0;
        for (int c = cols - 1; c >= 0; c--) 
        {
            for (int r = 0; r < rows; r++) 
            {
                grid[r][c] = encryptedMessage.substring(index, index + 1);
                index++;
            }
        }
        
        String decrypted = "";
        for (int r = 0; r < rows; r++) 
        {
            for (int c = 0; c < cols; c++) 
            {
                if (!grid[r][c].equals("=")) 
                {
                    decrypted += grid[r][c];
                }
            }
        }
        
        return decrypted;
    }
}