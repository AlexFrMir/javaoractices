
    static String send_message_election(String Host, int Port) throws IOException{
        Socket sclient;
        DataOutputStream soutput;
        DataInputStream sintput;
        String getdata;
        try{
            sclient = new Socket(Host,Port);
            if(sclient.isConnected()){
                soutput = new DataOutputStream(sclient.getOutputStream());
                sintput = new DataInputStream(sclient.getInputStream());
                soutput.writeUTF("ELECCION");
                getdata = sintput.readUTF();
                soutput.close();
                sintput.close();
                return(getdata);
            }else{
                return "";                
            }
        } catch (Exception e) {
            //TODO: handle exception
            
        }finally{
            sclient.close();
        }
    }
