static void read(DataInputStream f, byte[] b, int position, int lenght) throws Exception{
    while(lenght > 0){
        int n = f.read(b, position, lenght);
        position += n;
        lenght -= n;
    }
}

ByteBuffer b = ByteBuffer.allocate(5*8);

/*
    metodo estatico no requiere inicializarlo, se puede poner
    directamente.
*/ 