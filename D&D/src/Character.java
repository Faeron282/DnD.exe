import java.io.BufferedReader;
import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Character {
    boolean magicCaster;
    String[] Char;
    String newChar;
    String name;
    String race;
    String sex;
    String lvl;
    String cClass;
    String maxHealth;
    String curHealth;
    String[] spells;
    String[] stats;
    int curH;
    int maxH;
    String passivePerception;
    String passiveArcana;
    String armorClass;
    String[] maxSpellSlots;
    String[] curSpellSlots;
    String[] featsAndTraits;
    String[] equipment;
    String[] money;
    String[] statuseffects;
    public Character(String fName) {

            File file = new File(fName);

            if (!file.canRead() || !file.isFile())
                System.exit(0);

            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(fName));
                String zeile = null;
                while ((zeile = in.readLine()) != null) {
                    newChar = newChar + zeile;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null)
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
            }
            newChar = newChar.substring(newChar.length() - newChar.length() + 4);
            System.out.println(newChar);
            Char = newChar.split(";");
            name = Char[0];
            race = Char[1];
            sex = Char[2];
            lvl = Char[3];
            cClass = Char[4];
            maxHealth = Char[5];
            curHealth = Char[6];
            stats = Char[7].split(",");
            System.out.println(stats[0] + stats[3] + stats[5]);
            passivePerception = Char[8];
            passiveArcana = Char[9];
            armorClass = Char[10];
            maxSpellSlots = Char[11].split(",");
            curSpellSlots = Char[12].split(",");
            featsAndTraits = Char[13].split(",");
            equipment = Char[14].split(",");
            money = Char[15].split(",");
            spells = Char[16].split(",");
            statuseffects = Char[17].split(",");
            curH = Integer.parseInt(curHealth.substring(10));
            maxH = Integer.parseInt(maxHealth.substring(10));
            if (spells[0].equals("Spells:keine")) {
                magicCaster = false;
            }
        }
        public void command(String command, String param[]) {
        if (command.equals("getChar")) {
            printChar();
        }
        else if (command.equals("getAC")) {
            System.out.println(armorClass);
        }
        else if (command.equals("getSpells")) {
            printSpells();
        }
        else if (command.equals("heal")) {
            healDamage(Integer.parseInt(param[2]));
        }
        else if (command.equals("damage")) {
            dealDamage(Integer.parseInt(param[2]));
        }
        else if (command.equals("cast") || command.equals("castSpell")) {
            castSpell(Integer.parseInt(param[2]));
        }
        else if (command.equals("regainSpell") || command.equals("gainSpellSlot")) {
            int tier = Integer.parseInt(param[2]);
            int number;
            if (param.length > 4) {
                number  = Integer.parseInt(param[3]);
            }
            else {
                number = 1;
            }
            gainSpellSlot(tier, number);
        }
        else if (command.equals("spend")) {
            spendGold(param);
        }
        else if (command.equals("earn")) {
            earnGold(param);
        }
        else if (command.equals("save")) {
            saveChar(param[0]);
        }
        else if (command.equals("getEquipment") || command.equals("Equipment") || command.equals("equipment") || command.equals("getItem")) {
            printEquipment();
        }
        else if (command.equals("addEquipment") || command.equals("addItem")) {
            addItem(param[2]);
        }
        else if (command.equals("findEquipment") || command.equals("findItem") || command.equals("find")) {
            int pos = findItem(param[2]);
            if(pos != 69) {
                System.out.println("Das Item " + param[2] + " wurde an Position " + pos + " gefunden");
            }
            else{
                System.out.println("Das Item " + param[2] + " wurde nicht im Inventar des Spielers gefunden");
            }
        }
        else if (command.equals("removeEquipment") || command.equals("removeItem") || command.equals("remove")) {
            removeItem(param[2]);
        }
        }
        public void printChar() {
            System.out.println(name + "  " + race + "  " + sex + "  " + lvl + "  " + cClass + "  " + maxHealth + "  " + curHealth);
            System.out.println(money[0] + "  " + money[1] + "  " + money[2]);
            for (int n = 0; n < stats.length - 1; n++) {
                System.out.print(stats[n] + "  ");
            }
            System.out.println(stats[stats.length - 1]);
            System.out.println(passivePerception + "  " + passiveArcana + "  " + armorClass);
            if (magicCaster) {
                for (int n = 0; n < maxSpellSlots.length - 1; n++) {
                    System.out.print(maxSpellSlots[n] + "  ");
                }
                System.out.println(maxSpellSlots[maxSpellSlots.length - 1]);
                for (int n = 0; n < curSpellSlots.length - 1; n++) {
                    System.out.print(curSpellSlots[n] + "  ");
                }
                System.out.println(curSpellSlots[curSpellSlots.length - 1]);
            }
            for (int n = 0; n < featsAndTraits.length - 1; n++) {
                System.out.print(featsAndTraits[n] + "  ");
            }
            System.out.println(featsAndTraits[featsAndTraits.length - 1]);
            for (int n = 0; n < equipment.length - 1; n++) {
                System.out.print(equipment[n] + "  ");
            }
            System.out.println(equipment[equipment.length - 1]);
            if (magicCaster) {
                for (int n = 0; n < spells.length - 1; n++) {
                    System.out.print(spells[n] + "  ");
                }
                System.out.println(spells[spells.length - 1]);
            }
            if (statuseffects.length > 1) {
                for (int n = 0; n < statuseffects.length - 1; n++) {
                    System.out.print(statuseffects[n] + "  ");
                }
                System.out.println(statuseffects[statuseffects.length - 1]);
            }
        }
        public void addItem(String item){ //mir fällt hier auf anhieb keine Schönere Methode ein deshalb mache ich erstmal ein übergangarry
        String[] übergang = equipment;
        equipment = new String[equipment.length + 1];
        for (int loop = 0; loop < equipment.length - 1; loop++) {
            equipment[loop] = übergang[loop];
        }
        equipment[equipment.length - 1] = item;
        }
        public void removeItem(String item) {
            String[] übergang = new String[equipment.length - 1];
            int pos = findItem(item);
            boolean removed = false;
            if (pos != 69) {
                for (int loop1 = 0, loop2 = 0; loop2 < equipment.length; loop1++, loop2++) {
                    if (!removed) {
                        if (equipment[0].endsWith(item)) {
                            übergang[0] = "Equipment:" + equipment[1];
                            loop2++;
                            removed = true;
                            System.out.println("Das Item " + item + " wurde Erfolgreich entfernt Grüße <3");
                        } else if (loop1 == pos) {
                            übergang[loop1] = equipment[loop2 + 1];
                            loop2++;
                            removed = true;
                        }
                    } else {
                        übergang[loop1] = equipment[loop2];
                    }
                    equipment = übergang;
                }
            } else {
                System.out.println("Das Item " + item + " konnte nicht im Inventar des Spielers gefunden werden");
            }
        }
        public int findItem(String item) {
        int position = 69;
            for (int loop = 0; loop < equipment.length; loop++) {
                if (equipment[loop].endsWith(item)){
                    position = loop;
                    loop = equipment.length;
                }
            }
            return position;
            }
        public void printEquipment() {
            for (int n = 0; n < equipment.length - 1; n++) {
                System.out.print(equipment[n] + "  ");
            }
            System.out.println(equipment[equipment.length - 1]);
        }
        public void printSpells() {
            for(int n = 0; n < spells.length - 1; n++) {
                System.out.print(spells[n] + "  ");
            }
            System.out.println(spells[spells.length - 1]);
        }
        public void dealDamage (int damage) {
        curH = curH - damage;
            if (curH <= 0) {
                System.out.println("Der Charakter ist auf 0 Leben gefallen");
                curH = 0;
            }
            curHealth =  "CurHealth:" + curH;
            System.out.println("Der Charakter hat noch " + curH + " Leben übrig");
        }
        public void healDamage (int heal) {
        curH = curH + heal;
        if (curH > maxH) {
            curH = maxH;
        }
            curHealth = "CurHealth:" + curH;
            System.out.println("Der Charakter hat noch " + curH + " Leben übrig");
        }
        public void castSpell (int tier) {
            tier--; // um auf Arraygröße anzupassen, da cantrips nicht zählen
            if (tier >= curSpellSlots.length) {
                System.out.println("Der Charakter kann keine Zauber der Stufe " + ++tier + " verwenden. Maximal Zauber der Stufe " + curSpellSlots.length);
            } else {
                int cur = Integer.parseInt(curSpellSlots[tier].substring(curSpellSlots[tier].length() - 1)) - 1;
                if (cur < 0) {
                    System.out.println("Der Charakter hatte keine Slots mehr der Stufe " + ++tier + " und konnte den letzten Zauber nicht wirken");
                } else {
                    curSpellSlots[tier] = curSpellSlots[tier].substring(0, curSpellSlots[tier].length() - 1) + cur;
                    System.out.println("Es sind noch " + cur + " Slots von Tier " + (tier + 1) + " vorhanden");
                }
            }
        }
        public void gainSpellSlot (int tier, int number) {
            tier--; // um auf Arraygröße anzupassen, da cantrips nicht zählen
            if (tier >= curSpellSlots.length) {
                System.out.println("Der Charakter kann keine Zauber der Stufe " + ++tier + " verwenden. Maximal Zauber der Stufe " + curSpellSlots.length);
            } else {
                int cur = Integer.parseInt(curSpellSlots[tier].substring(curSpellSlots[tier].length() - 1)) + number;
                if (cur > Integer.parseInt(maxSpellSlots[tier].substring(maxSpellSlots[tier].length() - 1))) {
                    curSpellSlots[tier] = maxSpellSlots[tier];
                    System.out.println("Der Charakter hat nich so vile Slots mehr der Stufe " + ++tier + " und hat deshalb jetzt " +
                            Integer.parseInt(maxSpellSlots[tier].substring(maxSpellSlots[tier].length() - 1)) + " Spellslots übrig");
                } else {
                    curSpellSlots[tier] = curSpellSlots[tier].substring(0, curSpellSlots[tier].length() - 1) + cur;
                    System.out.println("Es sind noch " + cur + " Slots von Tier " + ++tier + " vorhanden");
                }
            }
        }
        public void spendGold(String[] expenses) {
            int gold;
            int silver;
            int copper;
            String temp;
            for (int loop = 2; loop < expenses.length; loop++) {
                temp = expenses[loop];
                if (temp.endsWith("g")) {
                    gold = Integer.parseInt(expenses[loop].substring(0, expenses[loop].length() - 1));
                    gold = Integer.parseInt(money[0].substring(5)) - gold;
                    money[0] = "Gold:" + gold;
                } else if (temp.endsWith("s")) {
                    silver = Integer.parseInt(expenses[loop].substring(0, expenses[loop].length() - 1));
                    silver = Integer.parseInt(money[1].substring(7)) - silver;
                    money[1] = "Silber:" + silver;
                } else if (temp.endsWith("c")) {
                    copper = Integer.parseInt(expenses[loop].substring(0, expenses[loop].length() - 1));
                    copper = Integer.parseInt(money[2].substring(7)) - copper;
                    money[2] = "Kupfer:" + copper;
                }
            }
            System.out.println("Der Charakter hat jetzt " + money[0] + "  " + money[1] + "  " + money[2]);
        }
        public void earnGold(String[] income) {
            int gold;
            int silver;
            int copper;
            String temp;
            for (int loop = 2; loop <income.length; loop++) {
                temp = income[loop];
                if (temp.endsWith("g")) {
                    gold = Integer.parseInt(income[loop].substring(0, income[loop].length() - 1));
                    gold = Integer.parseInt(money[0].substring(5)) + gold;
                    money[0] = "Gold:" + gold;
                } else if (temp.endsWith("s")) {
                    silver = Integer.parseInt(income[loop].substring(0, income[loop].length() - 1));
                    silver = Integer.parseInt(money[1].substring(7)) + silver;
                    money[1] = "Silber:" + silver;
                } else if (temp.endsWith("c")) {
                    copper = Integer.parseInt(income[loop].substring(0, income[loop].length() - 1));
                    copper = Integer.parseInt(money[2].substring(7)) + copper;
                    money[2] = "Kupfer:" + copper;
                }
            }
            System.out.println("Der Charakter hat jetzt" + money[0] + "  " + money[1] + "  " + money[2]);
        }
        public void saveChar(String filePath) {
                    String temp;
                    FileWriter writer;
                    File file = new File(filePath + name.substring(5) + "Char.txt");
                    try {
                        writer = new FileWriter(file);
                        writer.write(name + ";" + race + ";" + sex + ";" + lvl + ";" + cClass  + ";" + "\n");
                        writer.write(maxHealth + ";" + curHealth + ";" + "\n");
                        temp = stats[0];
                        for (int loop = 1; loop < stats.length; loop++) {
                            temp = temp + "," + stats[loop];
                        }
                        writer.write(temp + ";" + "\n");
                        writer.write(passivePerception + ";" + passiveArcana + ";" + armorClass + ";" + "\n");
                        temp = maxSpellSlots[0];
                        for (int loop = 1; loop < maxSpellSlots.length; loop++) {
                            temp = temp + "," + maxSpellSlots[loop];
                        }
                        writer.write(temp + ";" + "\n");
                        temp = curSpellSlots[0];
                        for (int loop = 1; loop < curSpellSlots.length; loop++) {
                            temp = temp + "," + curSpellSlots[loop];
                        }
                        writer.write(temp + ";" + "\n");
                        temp = featsAndTraits[0];
                        for (int loop = 1; loop < featsAndTraits.length; loop++) {
                            temp = temp + "," + featsAndTraits[loop];
                        }
                        writer.write(temp + ";" + "\n");
                        temp = equipment[0];
                        for (int loop = 1; loop < equipment.length; loop++) {
                            temp = temp + "," + equipment[loop];
                        }
                        writer.write(temp + ";" + "\n");
                        temp = money[0];
                        for (int loop = 1; loop < money.length; loop++) {
                            temp = temp + "," + money[loop];
                        }
                        writer.write(temp + ";" + "\n");
                        temp = spells[0];
                        for (int loop = 1; loop < spells.length; loop++) {
                            temp = temp + "," + spells[loop];
                        }
                        writer.write(temp + ";" + "\n");
                        temp = statuseffects[0];
                        for (int loop = 1; loop < statuseffects.length; loop++) {
                            temp = temp + "," + statuseffects[loop];
                        }
                        writer.write(temp);
                        writer.flush();
                        writer.close();
                        System.out.println("Speichern Erfolgreich");
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                        System.out.println("Fehler beim speichern");
                    }
            }
        }

