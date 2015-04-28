package com.kael.surf.net.logic;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelUpstreamHandler;

import com.kael.surf.net.codec.KProtobufDecoder;
import com.kael.surf.net.codec.KProtobufEncoder;
import com.kael.surf.net.codec.LengthFieldBasedFrameDecoder;

import static org.jboss.netty.channel.Channels.pipeline;

public class AppServerPipelineFactory implements ChannelPipelineFactory {

//    private CmdsConfig cmdsConfig;

    private ChannelUpstreamHandler handler;

    public AppServerPipelineFactory(ChannelUpstreamHandler handler) {
        this.handler = handler;
    }

    @Override
    public ChannelPipeline getPipeline() throws Exception {
        boolean isSkipPolicy = true;
        boolean isSkipTencentGwtHead = true;
        ChannelPipeline pipeline = pipeline();
        pipeline.addLast("framer", new LengthFieldBasedFrameDecoder(81920, 0, 4, 0, 4, isSkipPolicy, isSkipTencentGwtHead));
        pipeline.addLast("decoder", new KProtobufDecoder());
        pipeline.addLast("encoder", new KProtobufEncoder());
        pipeline.addLast("handler", handler);
        return pipeline;
    }

}
