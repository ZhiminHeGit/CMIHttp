import com.jcraft.jsch.*;

/**
 * This class handles SFTP connection creation. Created by zhimin on 4/29/16.
 */
// reference: https://atechblogagain.wordpress.com/2013/06/04/simple-sftp-example-in-java-with-jsch/
public class SFTPConnector {
    private Session session;
    private Channel channel;
    private ChannelSftp sftp = null;

    public void connect() throws JSchException, SftpException {
        if (sftp != null) {
            throw new RuntimeException("SFTPConnector already connected!");
        }
        String hostname = "10.32.42.201";
        String login = "cmiftp";
        String password = "JerryLion11";
        String directory = "tn_signal/xdr_http/20160221";

        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");

        JSch ssh = new JSch();
        session = ssh.getSession(login, hostname, 2222);
        session.setConfig(config);
        session.setPassword(password);
        session.connect();
        channel = session.openChannel("sftp");
        channel.connect();

        sftp = (ChannelSftp) channel;

        sftp.cd(directory);
    }

    public ChannelSftp getSftp() {
        if (sftp == null) {
            throw new RuntimeException("SFTPConnector not connected!");
        }
        return sftp;
    }

    public void disconnect() {

        channel.disconnect();
        session.disconnect();
        sftp = null;
    }

    public void finalize() throws Throwable {
        super.finalize();
        disconnect();
    }
}
