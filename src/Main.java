import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    int[][][] sudoku = new int[9][9][9];
    int durability =0;
    //Zapełnienie sudoku początkowymi liczbami: całe sudoku[pozycja x][pozycja y][liczba]=obecność
    startNumbersOfSudoku(sudoku);

    sudokuOut(sudoku);
    sudokuOutAll(sudoku);
    while (endOfProgram(sudoku)||durability==81) {
      //stawianie niemożliwości wypełnienia numerem
      noNumberInSquare(sudoku);

      sudokuOutAll(sudoku);
      //wypełnianie numerem gdy jest to jedyna możliwość w polu
      writeNumberInSquare(sudoku);


      //wyświetlanie sudoku
      sudokuOutAll(sudoku);
      sudokuOut(sudoku);
      durability++;
      if (durability == 81) System.out.print("Sudoku nie możliwe do rozwiązania w tej wersji programu");
    }


  }

  private static void sudokuOutAll(int[][][] sudoku) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        for (int k = 0; k < 9; k++) {
          System.out.print(sudoku[i][j][k]);
        }
        System.out.print(" ");
      }
      System.out.println();
    }
    System.out.println();
  }

  private static void sudokuOut(int[][][] sudoku) {
    System.out.print("-------------------");
    System.out.println();
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        for (int k = 0; k < 9; k++) {
          if (sudoku[i][j][k] == 1) {
            int z = k + 1;
            if(j%3==0) System.out.print("|" + z);
            else System.out.print(" " + z);
            break;
          } else if (k == 8) {
            if(j%3==0) System.out.print("|" + 0);
            else System.out.print(" " + 0);
          }
        }
      }
      System.out.print("|");
      if(i%3==2) {
        System.out.println();
        System.out.print("-------------------");
      }
      System.out.println();
    }
    System.out.println();
  }

  private static void writeNumberInSquare(int[][][] sudoku) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        int p;
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        int sum4 = 0;
        int sum5 = 0;
        int sum6 = 0;
        //Możliwość wpisania
        for (p = 0; p < 9; p++) {
          //sprawdzanie wierszy
          sum1 += sudoku[p][j][i];
          //sprawdzanie kolumn
          sum2 += sudoku[i][p][j];
          //sprawdzanie pola
          sum3 += sudoku[j][i][p];

          sum4 += sudoku[i][j][p];

          sum5 += sudoku[p][i][j];

          sum6 += sudoku[j][p][i];
        }
        //w danym polu wiersza tylko ta liczba
        if (sum1 == -8) {
          for (int k = 0; k < 9; k++) {
            if (sudoku[k][j][i] == 0) {
              sudoku[k][j][i] = 1;
            }
          }
        }
        //w danym polu kolumny tylko ta liczba
        if (sum2 == -8) {
          for (int k = 0; k < 9; k++) {
            if (sudoku[i][k][j] == 0) {
              sudoku[i][k][j] = 1;
            }
          }
        }
        //w danym polu żadna inna liczba
        if (sum3 == -8) {
          for (int k = 0; k < 9; k++) {
            if (sudoku[j][i][k] == 0) {
              sudoku[j][i][k] = 1;
            }
          }
        }
        if (sum3 == -7) {
          for (int k = 0; k < 9; k++) {
            if (sudoku[j][i][k] == 0) {
              for (int l = 0; l < 9; l++) {
                if (l != j) {
                  if (sudoku[l][i][k] == sudoku[j][i][k]) {
                    for (int m = 0; m < 9; m++) {
                      if (sudoku[j][i][m] == 0 && m != k) {
                        if (sudoku[l][i][m] == sudoku[j][i][m]) {
                          int suma = 0;
                          for (int r = 0; r < 9; r++) {
                            suma += sudoku[l][i][r];
                          }
                          if (suma == -7) {
                            //Gdy w kolumnie dwa pola z tylko dwoma tymi samymi możliwościami reszta pól w kolumnie ich nie może mieć
                            for (int o = 0; o < 9; o++) {
                              if (o != l) {
                                if (o != j) {
                                  sudoku[o][i][k] = -1;
                                  sudoku[o][i][m] = -1;

                                }
                              }
                            }
                            if (j / 3 == l / 3) {

                              for (int s = i / 3 * 3; s < 18 / (6 / (1 + i / 3)); s++) {
                                for (int n = j / 3 * 3; n < 18 / (6 / (1 + j / 3)); n++) {
                                  if (s != i && (n != j || n != l)) {
                                    int suma1 = 0;
                                    for (int o = 0; o < 9; o++) {
                                      if (sudoku[n][s][o] == -1) {
                                        suma1 += sudoku[n][s][o];
                                        if (suma1 == -7) {
                                          if (o == 8) {
                                            for (int q = 0; q < 9; q++) {
                                              if (sudoku[n][s][q] == 0) {
                                                if (q == m) {
                                                  for (int r = 0; r < 9; r++) {
                                                    if (r != m) {
                                                      if (sudoku[n][s][r] == 0) {
                                                        sudoku[n][s][r] = 1;
                                                      }
                                                    }
                                                  }
                                                }
                                                if (q == k) {
                                                  for (int r = 0; r < 9; r++) {
                                                    if (r != k) {
                                                      if (sudoku[n][s][r] == 0) {
                                                        sudoku[n][s][r] = 1;
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        if (sum4 == -7) {
          for (int k = 0; k < 9; k++) {
            if (sudoku[i][j][k] == 0) {
              for (int l = 0; l < 9; l++) {
                if (l != j) {
                  if (sudoku[i][l][k] == sudoku[i][j][k]) {
                    for (int m = 0; m < 9; m++) {
                      if (sudoku[i][j][m] == 0 && m != k) {
                        if (sudoku[i][l][m] == sudoku[i][j][m]) {
                          int suma = 0;
                          for (int r = 0; r < 9; r++) {
                            suma += sudoku[i][l][r];
                          }
                          if (suma == -7) {
                            for (int o = 0; o < 9; o++) {
                              if (o != l) {
                                if (o != j) {
                                  sudoku[i][o][k] = -1;
                                  sudoku[i][o][m] = -1;
                                }
                              }
                            }
                            if (j / 3 == l / 3) {
                              for (int s = i / 3 * 3; s < 18 / (6 / (1 + i / 3)); s++) {
                                for (int n = j / 3 * 3; n < 18 / (6 / (1 + j / 3)); n++) {
                                  if (s != i && n != j) {
                                    int suma1 = 0;
                                    for (int o = 0; o < 9; o++) {
                                      if (sudoku[s][n][o] == -1) {
                                        suma1 += sudoku[s][n][o];
                                        if (suma1 == -7) {
                                          if (o == 8) {
                                            for (int q = 0; q < 9; q++) {
                                              if (sudoku[s][n][q] == 0) {
                                                if (q == m) {
                                                  for (int r = 0; r < 9; r++) {
                                                    if (r != m) {
                                                      if (sudoku[s][n][r] == 0) {
                                                        sudoku[s][n][r] = 1;
                                                      }
                                                    }
                                                  }
                                                }
                                                if (q == k) {
                                                  for (int r = 0; r < 9; r++) {
                                                    if (r != k) {
                                                      if (sudoku[s][n][r] == 0) {
                                                        sudoku[s][n][r] = 1;
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          if (sum2 == -7) {
            for (int k = 0; k < 9; k++) {
              if (sudoku[i][k][j] == 0) {
                for (int l = 0; l < 9; l++) {
                  if (l != j) {
                    if (sudoku[i][k][l] == sudoku[i][k][j]) {
                      for (int m = 0; m < 9; m++) {
                        if (sudoku[i][m][j] == 0 && m != k) {
                          if (sudoku[i][m][l] == sudoku[i][m][j]) {
                            int suma = 0;
                            for (int r = 0; r < 9; r++) {
                              suma += sudoku[i][r][l];
                            }
                            if (suma == -7) {
                              for (int o = 0; o < 9; o++) {
                                if (o != l) {
                                  if (o != j) {
                                    sudoku[i][k][o] = -1;
                                    sudoku[i][m][o] = -1;
                                    if (k / 3 == m / 3) {
                                      for (int n = k / 3 * 3; n < 18 / (6 / (1 + k / 3)); n++) {
                                        for (int s = i / 3 * 3; s < 18 / (6 / (1 + i / 3)); s++) {
                                          if ((n != k && s != i) || (n != m & s != i)) {
                                            if (sudoku[s][n][j] == 0) {
                                              sudoku[s][n][j] = -1;
                                            }
                                            if (sudoku[s][n][l] == 0) {
                                              sudoku[s][n][l] = -1;
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          if (sum5 == -7) {
            for (int k = 0; k < 9; k++) {
              if (sudoku[k][i][j] == 0) {
                for (int l = 0; l < 9; l++) {
                  if (l != j) {
                    if (sudoku[k][i][l] == sudoku[k][i][j]) {
                      for (int m = 0; m < 9; m++) {
                        if (sudoku[m][i][j] == 0 && m != k) {
                          if (sudoku[m][i][l] == sudoku[m][i][j]) {
                            int suma = 0;
                            for (int r = 0; r < 9; r++) {
                              suma += sudoku[r][i][l];
                            }
                            if (suma == -7) {
                              for (int o = 0; o < 9; o++) {
                                if (o != l) {
                                  if (o != j) {
                                    sudoku[k][i][o] = -1;
                                    sudoku[m][i][o] = -1;
                                    if (k / 3 == m / 3) {
                                      for (int n = k / 3 * 3; n < 18 / (6 / (1 + k / 3)); n++) {
                                        for (int s = i / 3 * 3; s < 18 / (6 / (1 + i / 3)); s++) {
                                          if ((n != k && s != i) || (n != m & s != i)) {
                                            if (sudoku[n][s][j] == 0) {
                                              sudoku[n][s][j] = -1;
                                            }
                                            if (sudoku[n][s][l] == 0) {
                                              sudoku[n][s][l] = -1;
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          if (sum1 == -7) {
            for (int k = 0; k < 9; k++) {
              if (sudoku[k][j][i] == 0) {
                for (int l = 0; l < 9; l++) {
                  if (l != k) {
                    if (sudoku[l][j][i] == sudoku[k][j][i]) {
                      for (int m = 0; m < 9; m++) {
                        if (m != j) {
                          if (sudoku[l][m][i] == 0) {
                            if (sudoku[k][m][i] == 0) {
                              int suma = 0;
                              for (int n = 0; n < 9; n++) {
                                suma += sudoku[n][m][i];
                              }
                              if (suma == -7) {
                                for (int n = 0; n < 9; n++) {
                                  if (n != j) {
                                    if (n != m) {
                                      if (sudoku[k][n][i] == 0) {
                                        sudoku[k][n][i] = -1;
                                      }
                                      if (sudoku[l][n][i] == 0) {
                                        sudoku[l][n][i] = -1;
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                      if (k / 3 == l / 3) {
                        for (int n = 0; n < 9; n++) {
                          if (n != k / 3 * 3) {
                            if (n != k / 3 * 3 + 1) {
                              if (n != k / 3 * 3 + 2) {
                                if (sudoku[n][j][i] == 0) {
                                  sudoku[n][j][i] = -1;
                                }
                              }
                            }
                          }
                        }
                        for (int n = (k / 3 * 3); n < (18 / (6 / (1 + k / 3))); n++) {
                          for (int s = (j / 3 * 3); s < (18 / (6 / (1 + j / 3))); s++) {
                            if (s != j) {
                              if (sudoku[n][s][i] == 0) {
                                sudoku[n][s][i] = -1;
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }

          if (sum6 == -7) {
            for (int k = 0; k < 9; k++) {
              if (sudoku[j][k][i] == 0) {
                for (int l = 0; l < 9; l++) {
                  if (l != k) {
                    if (sudoku[j][l][i] == sudoku[j][k][i]) {
                      for (int m = 0; m < 9; m++) {
                        if (m != j) {
                          if (sudoku[m][l][i] == 0) {
                            if (sudoku[m][k][i] == 0) {
                              int suma = 0;
                              for (int n = 0; n < 9; n++) {
                                suma += sudoku[m][n][i];
                              }
                              if (suma == -7) {
                                for (int n = 0; n < 9; n++) {
                                  if (n != j) {
                                    if (n != m) {
                                      if (sudoku[n][k][i] == 0) {
                                        sudoku[n][k][i] = -1;
                                      }
                                      if (sudoku[n][l][i] == 0) {
                                        sudoku[n][l][i] = -1;
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                      if (k / 3 == l / 3) {
                        for (int n = 0; n < 9; n++) {
                          if (n != (k / 3 * 3)) {
                            if (n != k / 3 * 3 + 1) {
                              if (n != k / 3 * 3 + 2) {
                                if (sudoku[j][n][i] == 0) {
                                  sudoku[j][n][i] = -1;
                                }
                              }
                            }
                          }
                        }
                        for (int n = (k / 3 * 3); n < (18 / (6 / (1 + k / 3))); n++) {
                          for (int s = (j / 3 * 3); s < (18 / (6 / (1 + j / 3))); s++) {
                            if (s != j) {
                              if (sudoku[s][n][i] == 0) {
                                sudoku[s][n][i] = -1;
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  private static void noNumberInSquare(int[][][] sudoku) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        for (int k = 0; k < 9; k++) {
          if (sudoku[k][j][i] == 1) {
            for (int l = 0; l < 9; l++) {
              if (l != i) {
                if (sudoku[k][j][l] == 0) {
                  sudoku[k][j][l] = -1;
                }
              }
              if (l != j) {
                if (sudoku[k][l][i] == 0) {
                  sudoku[k][l][i] = -1;
                }
              }
              if (l != k) {
                if (sudoku[l][j][i] == 0) {
                  sudoku[l][j][i] = -1;
                }
              }
            }
            //czyszczenie kwadracika - do pomniejszenia lub metody
            for (int n = (k / 3 * 3); n < (18 / (6 / (1 + k / 3))); n++) {
              for (int s = (j / 3 * 3); s < (18 / (6 / (1 + j / 3))); s++) {
                if (sudoku[n][s][i] == 0) {
                  sudoku[n][s][i] = -1;
                }
              }
            }
          }
        }
      }
    }
  }

  private static void startNumbersOfSudoku(int[][][] sudoku) {
    Scanner scan = new Scanner(System.in);
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        System.out.println("Podaj cyfre w kolumnie " + j + " i wierszu " + i + ". Jeżeli pusta wpisz 0");
        int in = Integer.parseInt(scan.nextLine()) - 1;
        if (in != -1) {
          sudoku[i][j][in] = 1;
          for (int k = 0; k < 9; k++) {
            if (k != in) {
              sudoku[i][j][k] = -1;
            }
          }
        }
      }
    }
  }

  private static boolean endOfProgram(int[][][] sudoku) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        for (int k = 0; k < 9; k++) {
          if (sudoku[i][j][k] == 0) {
            return true;
          }
        }
      }
    }
    return false;
  }
}