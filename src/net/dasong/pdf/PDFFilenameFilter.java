package net.dasong.pdf;

import java.io.File;
import java.io.FilenameFilter;

public class PDFFilenameFilter implements FilenameFilter
{

    @Override
    public boolean accept(File file, String fileName)
    {
        // TODO Auto-generated method stub
        if (fileName.endsWith(".pdf"))
        {
            return true;
        }

        return false;
    }
}
