package ro.pub.cs.systems.eim.practicaltest02v4.network;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ro.pub.cs.systems.eim.practicaltest02v4.general.Constants;
import ro.pub.cs.systems.eim.practicaltest02v4.general.Utilities;

public class ClientThread extends Thread {

    private final String address;
    private final int port;
    private final String url;
    private final TextView httpDataTextView;

    private Socket socket;

    public ClientThread(String address, int port, String url, TextView httpDataTextView) {
        this.address = address;
        this.port = port;
        this.url = url;
        this.httpDataTextView = httpDataTextView;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);
            printWriter.println(url);
            printWriter.flush();
            String httpData;
            while ((httpData = bufferedReader.readLine()) != null) {
                final String finalizedHttpData = httpData;
                httpDataTextView.post(() -> httpDataTextView.setText(finalizedHttpData));
            }
        } catch (IOException ioException) {
            Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
                }
            }
        }
    }

}
