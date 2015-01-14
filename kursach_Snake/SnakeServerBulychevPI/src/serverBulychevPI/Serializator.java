/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverBulychevPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import uiUtilitiesBulychevPI.SavingObject;




/**
 *
 * @author HP
 */
public class Serializator {
    
    public boolean serialization(InputStream input ,String fileName) throws ClassNotFoundException{
//        Path dest=Paths.get(fileName);
//        Files.copy(input,dest);
        boolean flag=false;
        File f=new File("src/sav/"+fileName);
        ObjectOutputStream ostream=null;
        ObjectInputStream istream=null;
        try{
            FileOutputStream fos= new FileOutputStream(f,false);
            if(fos!=null){
                ostream=new ObjectOutputStream(fos);
                //byte[] buffer=null;
                                

                istream=new ObjectInputStream(input);
                SavingObject ex=(SavingObject) istream.readObject();
                System.out.println("Saving: "+fileName);
                ostream.writeObject(ex);
                
                flag=true;
            }
    }catch(FileNotFoundException e){
        System.err.println("Файл не может быть создан: "+e);
    }catch (NotSerializableException e) {
        System.err.println("Класс не поддерживает сериализацию: " + e);
    } catch (IOException e) {
        System.err.println(e);
    } finally {
            try {
                if (ostream != null) 
                        ostream.close();

            } catch (IOException e) {
                System.err.println("ошибка закрытия потока");
            }
        }
        return flag;
    }
    public void deserialization(OutputStream output,String fileName) throws InvalidObjectException, ClassNotFoundException {
        File fr = new File("src/sav/"+fileName);
        ObjectInputStream istream = null;
        ObjectOutputStream ostream = null;
        //PrintStream ps = new PrintStream(output);
        try {
        
            FileInputStream fis = new FileInputStream(fr);
            istream = new ObjectInputStream(fis);
            ostream= new ObjectOutputStream(output);
            //ps.println("begin");
            // десериализация
            
            SavingObject ex=(SavingObject) istream.readObject();
            System.out.println("Cохранение '"+fileName+"' отправленно:");
            System.out.println("Очки: "+ex.getScore()+"  Яблоки: "+ex.getApples()+
                    "  Время: "+ex.getTimer().getHours()+":"+ex.getTimer().getMinutes()+
                    ":"+ex.getTimer().getSeconds()+"\n");
            //Thread.sleep(10000);
            //ostream.writeObject(ex);
            //SavingObject st = (SavingObject) istream.readObject();
            
        } catch (FileNotFoundException e) {
            System.err.println("Файл для десериализации не существует: "+ e);
        } catch (InvalidClassException ioe) {
            System.err.println("Несовпадение версий классов: " + ioe);
        } catch (IOException ioe) {
            System.err.println("Общая I/O ошибка: " + ioe);
        }
        finally {
            try {
                if (istream != null)  istream.close();
            } catch (IOException e) {
            System.err.println("ошибка закрытия потока ");
            }
        }
        //throw new InvalidObjectException("объект не восстановлен");
    }
    public boolean exist(String fileName)
    {
        boolean exist;
        File fr = new File(fileName);
        exist=fr.exists();
        return exist;
    }
        public boolean creatF(String fileName) throws IOException
    {
        boolean create;
        File fr = new File(fileName);
        create=fr.createNewFile();
        return create;
    }
}
