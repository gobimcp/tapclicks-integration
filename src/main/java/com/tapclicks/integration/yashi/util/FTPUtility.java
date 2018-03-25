package com.tapclicks.integration.yashi.util;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;

import com.jcraft.jsch.Session;

public class FTPUtility {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void downloadFileFromFTPServer(String server, String port, String user, String password,
			String serverDir, String localDir, String fileFormat) throws Exception {
		JSch jsch = null;
		Session session = null;
		Channel channel = null;
		try {
			jsch = new JSch();
			session = jsch.getSession(user, server, Integer.parseInt(port));
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);
			session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			Vector<ChannelSftp.LsEntry> files = sftpChannel.ls(serverDir);
			for (ChannelSftp.LsEntry file : files) {
				if (file.getFilename().startsWith(fileFormat)) {
					sftpChannel.get(serverDir + file.getFilename(), localDir);
				}
			}

			sftpChannel.exit();
			session.disconnect();

			session = null;
			sftpChannel = null;

		} catch (Exception e) {
			throw e;			
		}

	}

}
