package com.sandbox.outlook;

/**
 * JACOB Outlook sample contributed by
 * Christopher Brind <christopher.brind@morse.com>
 */

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * sample class to show simple outlook manipulation
 */
public class Outlook {

    private static String pad(int i) {
        StringBuffer sb = new StringBuffer();

        while (sb.length() < i) {
            sb.append(' ');
        }

        return sb.toString();
    }

    private static void recurseFolders(int iIndent, Dispatch o) {

        if (o == null) {
            return;
        }
        Dispatch oFolders = Dispatch.get(o, "Folders").toDispatch();
        System.out.println("oFolders=" + oFolders);
        if (oFolders == null) {
            return;
        }

        try {
            Dispatch oFolder = Dispatch.get(oFolders, "GetFirst").toDispatch();
            do {
                Object oFolderName = Dispatch.get(oFolder, "Name");
                if (null == oFolderName) {
                    break;
                }

                System.out.println(pad(iIndent) + oFolderName);
                recurseFolders(iIndent + 3, oFolder);

                oFolder = Dispatch.get(oFolders, "GetNext").toDispatch();
            } while (true);
        } catch (IllegalStateException e) {
            System.out.println("Its ok!");
        }


    }

    /**
     * standard run loop
     *
     * @param asArgs command line arguments
     * @throws Exception
     */
    public static void main(String asArgs[]) throws Exception {
        System.out.println("Outlook: IN");

        ActiveXComponent axOutlook = new ActiveXComponent("Outlook.Application");
        try {
            System.out.println("version=" + axOutlook.getProperty("Version"));

            Dispatch oOutlook = axOutlook.getObject();
            System.out.println("version=" + Dispatch.get(oOutlook, "Version"));

            Dispatch oNameSpace = axOutlook.getProperty("Session").toDispatch();
            System.out.println("oNameSpace=" + oNameSpace);

//            recurseFolders(0, oNameSpace);
            Dispatch.call(oOutlook ,"GetNamespace","MAPI").toDispatch();
            Dispatch email = Dispatch.invoke(axOutlook.getObject(),"CreateItem", Dispatch.Get, new Object[] { "0" }, new int[0]).toDispatch();
            Dispatch.put(email, "To", "dkachurovskiy@luxoft.com");
            Dispatch.put(email, "Subject", "Tst");
            Dispatch.put(email, "Body", "Some text");
//            Dispatch.put(email, "Body", getCuerpoEmail("C:\\archivo.html"));
            Dispatch.put(email, "ReadReceiptRequested", "false");
            try {
                Dispatch.call(email, "Send");
            } catch (com.jacob.com.ComFailException e) {
                e.printStackTrace();
            }
        } finally {
//            axOutlook.invoke("Quit", new Variant[] {});
        }
    }

    public static class MailItemEventHandler {

        public void AttachmentAdd(Variant[] arguments) {
            System.out.println("AttachmentAdd");
        }

        public void AttachmentRead(Variant[] arguments) {
            System.out.println("AttachmentRead");
        }

        public void AttachmentRemove(Variant[] arguments) {
            System.out.println("AttachmentRemove");
        }

        public void BeforeAttachmentAdd(Variant[] arguments) {
            System.out.println("BeforeAttachmentAdd");
        }

        public void BeforeAttachmentPreview(Variant[] arguments) {
            System.out.println("BeforeAttachmentPreview");
        }

        public void BeforeAttachmentRead(Variant[] arguments) {
            System.out.println("BeforeAttachmentRead");
        }

        public void BeforeAttachmentSave(Variant[] arguments) {
            System.out.println("BeforeAttachmentSave");
        }

        public void BeforeAttachmentWriteToTempFile(Variant[] arguments) {
            System.out.println("BeforeAttachmentWriteToTempFile");
        }

        public void BeforeAutoSave(Variant[] arguments) {
            System.out.println("BeforeAutoSave");
        }

        public void BeforeCheckNames(Variant[] arguments) {
            System.out.println("BeforeCheckNames");
        }

        public void BeforeDelete(Variant[] arguments) {
            System.out.println("BeforeDelete");
        }

        public void Close(Variant[] arguments) {
            System.out.println("Close");
        }

        public void CustomAction(Variant[] arguments) {
            System.out.println("CustomAction");
        }

        public void CustomPropertyChange(Variant[] arguments) {
            System.out.println("CustomPropertyChange");
        }

        public void Forward(Variant[] arguments) {
            System.out.println("Forward");
        }

        public void Open(Variant[] arguments) {
            System.out.println("Open");
        }

        public void PropertyChange(Variant[] arguments) {
            System.out.println("PropertyChange");
        }

        public void Read(Variant[] arguments) {
            System.out.println("Read");
        }

        public void Reply(Variant[] arguments) {
            System.out.println("Reply");
        }

        public void ReplyAll(Variant[] arguments) {
            System.out.println("ReplyAll");
        }

        public void Send(Variant[] arguments) {
            System.out.println("Send");
        }

        public void Unload(Variant[] arguments) {
            System.out.println("Unload");
        }

        public void Write(Variant[] arguments) {
            System.out.println("Write");
        }

    }

}