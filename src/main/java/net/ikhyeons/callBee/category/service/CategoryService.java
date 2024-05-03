package net.ikhyeons.callBee.category.service;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.ikhyeons.callBee.category.Category;
import net.ikhyeons.callBee.category.repository.CategoryRepository;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.channel.repository.ChannelRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ChannelRepository channelRepository;

    public Long addCategory(Long channelId, String name){
        Channel channel = channelRepository.findById(channelId).get();
        Category category = new Category();
        category.init("공지사항", channel);

        categoryRepository.save(category);
        category.rename(name);
        channel.addCateogry(category);

        return category.getId();
    };

    public Long deleteCategory(Long categoryId){
        Category category = categoryRepository.findById(categoryId).get();
        category.delete();
        return category.getId();
    };

    public Long renameCategory(Long id, String name){
        Category category = categoryRepository.findById(id).get();
        category.rename(name);

        return category.getId();
    };

}
