
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class manages the entire GUI
 */
public class GUI {
    private static final String BACK = "C:\\Users\\Nathan\\Documents\\PnP\\Eigene Gruppe\\Die Welt\\";
    String command;
    BufferedImage bImg0;
    BufferedImage bImg1;
    BufferedImage bImg2;
    BufferedImage bImg3;
    BufferedImage bImg;
    ImageIcon icon0;
    ImageIcon icon1;
    ImageIcon icon2;
    ImageIcon icon3;
    JFrame DND;
    double width;
    double height;
    Character Bri = new Character(BACK + "CharsAndSpells\\" + "BriChar.txt"); //Jonas Charakter
   /* Character Teleria = new Character(BACK + "CharsAndSpells\\" + "TeleriaChar.txt"); // Fabiennes Charakter
    Character Holg = new Character(BACK + "CharsAndSpells\\" + "HolgChar.txt"); //Max Charakter
    Character Suzan = new Character(BACK + "CharsAndSpells\\" + "SuzanChar.txt"); //Suzan Charakter */
    public GUI() {
        Scanner eingabe = new Scanner(System.in);
        DND = new JFrame("DND");
        DND.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DND.setExtendedState(JFrame.MAXIMIZED_BOTH);
        DND.getContentPane().setLayout(new GridLayout(1, 1));
        JLabel background = new JLabel();
        DND.add(background, 0, 0);
        background.setLayout(new GridLayout(1, 2));
        JLabel jLLeft = new JLabel();
        background.add(jLLeft, 0, 0);
        JLabel jLRight = new JLabel();
        background.add(jLRight, 0, 1);
        jLLeft.setLayout(new GridLayout(2, 1));
        JLabel jLLeftTop = new JLabel();
        jLLeft.add(jLLeftTop, 0, 0);
        JLabel jLLeftBottom = new JLabel();
        jLLeft.add(jLLeftBottom, 0, 1);
        jLRight.setLayout(new GridLayout(2, 1));
        JLabel jLRightTop = new JLabel();
        jLRight.add(jLRightTop, 0, 0);
        JLabel jLRightBottom = new JLabel();
        jLRight.add(jLRightBottom, 0, 1);
        background.setVisible(true);
        jLLeft.setVisible(false);
        jLRight.setVisible(false);
        jLLeftTop.setVisible(false);
        jLLeftBottom.setVisible(false);
        jLRightTop.setVisible(false);
        jLRightBottom.setVisible(false);
        DND.setVisible(true);
        try {
            bImg = ImageIO.read(new File(BACK + "Donnerwacht.png"));
        } catch (IOException ex) {
            System.out.println("Hier sollte es nicht sein");
        }
        ImageIcon icon = getIcon(bImg, background.getWidth(), background.getHeight());
        background.setIcon(icon);
        /**
         *TODO
         * 1. implement an Audio output (music and other sounds)
         * 2. fix the problem with the resulution (if possible the howle screen should be used)
         * 3. perfect background (not the Bar)
         */
        int qH = jLLeftBottom.getHeight();
        int qW = jLLeftBottom.getWidth();
        int hH = jLLeft.getHeight();
        int hW = jLLeft.getWidth();
        int fH = background.getHeight();
        int fW = background.getWidth();
        while (true) {
            String[] param;
            command = eingabe.next();
            param = command.split(",");
            if (command.equals("exit")) {
                System.exit(0);
            } else if (command.equals("null")) {
                background.setVisible(true);
                jLLeft.setVisible(false);
                jLRight.setVisible(false);
                jLLeftTop.setVisible(false);
                jLLeftBottom.setVisible(false);
                jLRightTop.setVisible(false);
                jLRightBottom.setVisible(false);
                DND.setVisible(true);
                icon0 = null;
                icon1 = null;
                icon3 = null;
                icon3 = null;
                icon = getIcon(bImg, fW, fH);
                background.setIcon(icon);
                /**
                 * Change an Image at the speciefied positon
                 * Input c,*position*,*new Image*
                 */
            } else if (param[0].equals("c")) {
                String pos = param[1];
                if (pos.equals("0")) {
                    if (icon1 == null) {
                        try {
                            bImg0 = ImageIO.read(new File(BACK + param[2]));
                        } catch (IOException ex) {
                            System.out.println("Hier sollte es nicht sein");
                        }
                        icon0 = getIcon(bImg0, fW, fH);
                        background.setIcon(icon0);
                    } else if (icon2 == null) {

                        try {
                            bImg0 = ImageIO.read(new File(BACK + param[2]));
                        } catch (IOException ex) {
                            System.out.println("Hier sollte es nicht sein");
                        }
                        icon0 = getIcon(bImg0, hW, hH);
                        jLLeft.setIcon(icon0);
                    } else {
                        try {
                            bImg0 = ImageIO.read(new File(BACK + param[2]));
                        } catch (IOException ex) {
                            System.out.println("Hier sollte es nicht sein");
                        }
                        icon0 = getIcon(bImg0, qW, qH);
                        jLLeftTop.setIcon(icon0);
                    }
                } else if (pos.equals("1")) {
                    if (icon1 != null && icon0 != null) {
                        if (icon2 == null) {
                            try {
                                bImg1 = ImageIO.read(new File(BACK + param[2]));
                            } catch (IOException ex) {
                                System.out.println("Hier sollte es nicht sein");
                            }
                            icon1 = getIcon(bImg2, hW, hH);
                            jLRight.setIcon(icon1);
                        } else {
                            try {
                                bImg2 = ImageIO.read(new File(BACK + param[2]));
                            } catch (IOException ex) {
                                System.out.println("Hier sollte es nicht sein");
                            }
                            icon2 = getIcon(bImg2, qW, qH);
                            jLLeftBottom.setIcon(icon2);
                        }
                    }
                } else if (pos.equals("2")) {
                    if (icon0 != null && icon1 != null && icon2 != null) {
                        if (icon3 == null) {
                            try {
                                bImg1 = ImageIO.read(new File(BACK + param[2]));
                            } catch (IOException ex) {
                                System.out.println("Hier sollte es nicht sein");
                            }
                            icon1 = getIcon(bImg1, hW, hH);
                            jLRight.setIcon(icon1);
                        } else {
                            try {
                                bImg1 = ImageIO.read(new File(BACK + param[2]));
                            } catch (IOException ex) {
                                System.out.println("Hier sollte es nicht sein");
                            }
                            icon1 = getIcon(bImg1, qW, qH);
                            jLRightTop.setIcon(icon1);
                        }
                    }
                } else if (icon0 != null && icon1 != null && icon2 != null && icon3 != null) {
                    try {
                        bImg3 = ImageIO.read(new File(BACK + param[2]));
                    } catch (IOException ex) {
                        System.out.println("Hier sollte es nicht sein");
                    }
                    icon3 = getIcon(bImg3, qW, qH);
                    jLRightBottom.setIcon(icon3);
                }
                /** javadoc
                 * Delete an image at the specified position
                 * input d,*pos*
                 *
                 */
            } else if (param[0].equals("d")) {
                String pos = param[1];
                if (pos.equals("3")) {
                    if (icon0 != null && icon1 != null && icon2 != null && icon3 != null) {
                        icon1 = getIcon(bImg1, hW, hH);
                        jLRight.setIcon(icon1);
                        jLRightTop.setVisible(false);
                        jLRightBottom.setVisible(false);
                        icon3 = null;
                    }
                } else if (pos.equals("2")) {
                    if (icon0 != null && icon1 != null && icon2 != null) {
                        if (icon3 != null) {
                            icon1 = getIcon(bImg1, hW, hH);
                            jLRight.setIcon(icon2);
                            jLRightBottom.setVisible(false);
                            jLRightTop.setVisible(false);
                            icon3 = null;
                        } else {
                            if (icon3 == null) {
                                icon0 = getIcon(bImg0, hW, hH);
                                jLLeft.setIcon(icon0);
                                jLLeftTop.setVisible(false);
                                jLLeftBottom.setVisible(false);
                                icon1 = getIcon(bImg2, hW, hH);
                                jLRight.setIcon(icon1);
                                jLRightTop.setVisible(false);
                                jLRightBottom.setVisible(false);
                                bImg1 = bImg2;
                                icon2 = null;
                            }
                        }
                    }
                } else if (pos.equals("1")) {
                    if (icon0 != null && icon1 != null && icon2 != null) {
                        icon0 = getIcon(bImg0, hW, hH);
                        icon2 = null;
                        jLLeft.setIcon(icon0);
                        jLLeftBottom.setVisible(false);
                        jLLeftTop.setVisible(false);
                    } else if (icon0 != null && icon1 != null) {
                        icon0 = getIcon(bImg0, fW, fH);
                        icon1 = null;
                        background.setIcon(icon0);
                        jLLeft.setVisible(false);
                        jLRight.setVisible(false);
                    }
                } else if (pos.equals("0")) {
                    if (icon0 != null && icon1 != null && icon2 != null) {
                        icon0 = getIcon(bImg1, hW, hH);
                        bImg0 = bImg1;
                        icon1 = getIcon(bImg2, hW, hH);
                        bImg1 = bImg2;
                        icon2 = null;
                        jLLeft.setIcon(icon0);
                        jLLeftBottom.setVisible(false);
                        jLLeftTop.setVisible(false);
                    } else if (icon0 != null && icon1 != null) {
                        icon0 = getIcon(bImg1, fW, fH);
                        bImg0 = bImg1;
                        icon1 = null;
                        jLLeft.setVisible(false);
                        jLRight.setVisible(false);
                        background.setIcon(icon0);
                    } else {
                        ImageIcon icon0 = getIcon(bImg, background.getWidth(), background.getHeight());
                        background.setIcon(icon0);
                    }
                }
            }
            /**javadoc
             * AddMethode
             * input "add" + *name*+*format*
             *
             */
            else if (param[0].equals("add")) {
                if (icon0 == null) {
                    try {
                        bImg0 = ImageIO.read(new File(BACK + param[1]));
                    } catch (IOException ex) {
                        System.out.println("Hier sollte es nicht sein");
                    }
                    icon0 = getIcon(bImg0, fW, fH);
                    background.setIcon(icon0);
                } else if (icon1 == null) {
                    try {
                        bImg1 = ImageIO.read(new File(BACK + param[1]));
                    } catch (IOException ex) {
                        System.out.println("Hier sollte es nicht sein");
                    }
                    icon1 = getIcon(bImg1, hW, hH);
                    icon0 = getIcon(bImg0, hW, hH);
                    jLLeft.setIcon(icon0);

                    jLRight.setIcon(icon1);
                    jLLeft.setVisible(true);
                    jLRight.setVisible(true);
                    background.setIcon(null);
                } else if (icon2 == null) {
                    try {
                        bImg2 = ImageIO.read(new File(BACK + param[1]));
                    } catch (IOException ex) {
                        System.out.println("Hier sollte es nicht sein");
                    }
                    icon2 = getIcon(bImg2, qW, qH);
                    icon0 = getIcon(bImg0, qW, qH);
                    jLLeftTop.setIcon(icon0);
                    jLLeftBottom.setIcon(icon2);
                    jLLeft.setIcon(null);
                    jLLeftTop.setVisible(true);
                    jLLeftBottom.setVisible(true);
                } else if (icon3 == null) {
                    try {
                        bImg3 = ImageIO.read(new File(BACK + param[1]));
                    } catch (IOException ex) {
                        System.out.println("Hier sollte es nicht sein");
                    }
                    icon3 = getIcon(bImg3, qW, qH);
                    icon1 = getIcon(bImg1, qW, qH);
                    jLRightTop.setIcon(icon1);
                    jLRightBottom.setIcon(icon3);
                    jLRight.setIcon(null);
                    jLRightTop.setVisible(true);
                    jLRightBottom.setVisible(true);

                }

            } else if (param[0].equals("Bri")) {
                if (param.length > 2) {
                    Bri.command(param[1], param);
                }
                else {
                    String[] fileP = new String[]{BACK + "CharsAndSpells\\"};
                            Bri.command(param[1], fileP);
                }
            } /*else if (param[0].equals("Teleria")) {
                String[] teleria = Teleria.command(param[1]);
            } else if (param[0].equals("Holg")) {
                String[] teleria = Holg.command(param[1]);
            } else if (param[0].equals("Suzan")) {
                String[] teleria = Suzan.command(param[1]);
            } */
        }
    }
        public ImageIcon getIcon(BufferedImage bIm, int w, int h){
        width = bIm.getWidth();
        height = bIm.getHeight();
        if(height <= width) {
            h = (int)((height/width) *( w * 0.8));
        }
        else {
            w = (int)(((width/ height) * h));
        }
            Image img = bIm.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
        }
}
