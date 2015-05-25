package com.elearning.web.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


/**
 *  压缩谋一目录,或单个文件, 
 *  支持中文文件名称. 
 *  
 */
public class Zipper
{
	
	   static IOFileFilter filter = new IOFileFilter()
	    {

	        public boolean accept(java.io.File file)
	        {
	            return true;
	        }

	        public boolean accept(java.io.File dir, java.lang.String name)
	        {
	            return true;
	        }
	    };
    /**
     * 压缩某一目录 , added by chenbj
     * @param dirpath
     * @param zipfile
     * @return
     */
    public static boolean zip(String sourceDir, String zipFile)
    {
    	        OutputStream os;  
    	        boolean bret = true;
    	        try 
    	        {  
    	            os = new FileOutputStream(zipFile);  
    	            BufferedOutputStream bos = new BufferedOutputStream(os);  
    	            ZipOutputStream zos = new ZipOutputStream(bos);  
    	            File file = new File(sourceDir);  
    	            Collection cfile = null;
    	            File basepath = null;
    	            if (file.isDirectory()) 
    	            {
    	            	basepath = file;
    	                cfile = FileUtils.listFiles(new File(
    	                		sourceDir), filter, filter);
    	                
    	               
    	            } else 
    	            {
    	            	ArrayList al = new ArrayList();
    	            	al.add(file);
    	            	cfile = al;
    	            	basepath = file.getParentFile();
    	            	
    	            }  
    	            
    	            Iterator it = cfile.iterator();
    	            while (it.hasNext())
    	            {
    	                File f = (File) it.next();
    	                zipOneFile(zos,  f, basepath);
    	            }
    	            zos.closeEntry();  
    	            zos.close();  
    	  
    	        } catch (Exception e) {  
    	            e.printStackTrace();  
    	            bret = false;
    	  
    	        }  
    	        return bret;
    	  
    	   
    }
    
    	
 private static  boolean zipOneFile(ZipOutputStream out, File f, File base)throws Exception
    		    {
    		        String name = f.getPath().replace('\\', '/');
    		        boolean b =true;
    		        if (base != null)
    		        {
    		            String basename = base.getPath().replace('\\', '/');
    		            
    		            if (name.startsWith(basename))
    		            {
    		                name = name.substring(basename.length());
    		            }
    		        }

    		        if (name.startsWith("/"))
    		        {
    		            name = name.substring(1);
    		        }

    		        ZipEntry entry = new ZipEntry(name);
    		        entry.setTime(f.lastModified());

    		        out.putNextEntry(entry);

    		        FileInputStream is = new FileInputStream(f);

    		        byte[] buf = new byte[2048];

    		        try
    				{
    		        int l;
    		        while ((l = is.read(buf)) > -1)
    		        {
    		            out.write(buf, 0, l);
    		        }
    				}
    		        finally
    				{
    		        	is.close();
    				}
    		      //  out.closeEntry();

    		        return b;
    		        
   }
    	
    public static void main(String[] args) throws Exception
    {
    	zip("D:\\temp\\ccc", "d:\\temp\\us.zip");

    }
}

