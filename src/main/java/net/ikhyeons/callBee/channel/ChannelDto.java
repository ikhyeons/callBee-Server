package net.ikhyeons.callBee.channel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDto {
    private String channelTitle;
    private String channelCategory;
    private String channelSubtitle;
    private String channelBoard;
}
