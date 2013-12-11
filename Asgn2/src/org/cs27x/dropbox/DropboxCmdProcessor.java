package org.cs27x.dropbox;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

import org.cs27x.dropbox.DropboxCmd.OpCode;
import org.cs27x.filewatcher.FileState;
import org.cs27x.filewatcher.FileStates;

public class DropboxCmdProcessor implements DropboxTransportListener {

    private final FileManager fileManager_;

    private final FileStates fileStates_;

    public DropboxCmdProcessor(final FileStates states, final FileManager mgr) {
        super();
        fileStates_ = states;
        fileManager_ = mgr;
    }

    public void updateFileState(final DropboxCmd cmd, final Path resolved) {
        try {
            if (cmd.getOpCode() == OpCode.REMOVE) {
                final FileState state = fileStates_.getState(resolved);
                if(state != null){
                    state.setSize(-1);
                }
            } else if (cmd.getOpCode() == OpCode.ADD
                    || cmd.getOpCode() == OpCode.UPDATE) {
                final FileState state = fileStates_.getOrCreateState(resolved);
                state.setSize(cmd.getData().length);
                state.setLastModificationDate(Files.getLastModifiedTime(resolved));
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cmdReceived(final DropboxCmd cmd) {
        try {

            final Path resolved = fileManager_.resolve(cmd.getPath());
            final OpCode op = cmd.getOpCode();

            if (op == OpCode.ADD || op == OpCode.UPDATE) {
                fileManager_
                        .write(resolved, cmd.getData(), op == OpCode.UPDATE);
            } else if (op == OpCode.REMOVE) {
                fileManager_.delete(resolved);
            }

            updateFileState(cmd, resolved);

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void connected(final DropboxTransport t) {

    }

    @Override
    public void disconnected(final DropboxTransport t) {

    }

}
