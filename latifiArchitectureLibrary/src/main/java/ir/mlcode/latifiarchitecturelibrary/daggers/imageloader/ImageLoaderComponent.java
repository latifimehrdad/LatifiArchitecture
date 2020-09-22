package ir.mlcode.latifiarchitecturelibrary.daggers.imageloader;


import com.nostra13.universalimageloader.core.ImageLoader;

import dagger.Component;

@Component(modules = ImageLoaderModule.class)
public interface ImageLoaderComponent {
    ImageLoader getImageLoader();
}