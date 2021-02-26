package com.cvaiedu.template.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangyi
 * @date 2018-10-01 15:38
 */
public class TreeUtil {

    /**
     * 两层循环实现建树
     *
     * @param treeEntities 传入的树实体列表
     * @return List
     */
    public static <T> List<T> buildTree(List<? extends TreeEntity<T>> treeEntities, Long root) {
        List<TreeEntity<T>> treeEntityArrayList = new ArrayList<>();
        treeEntities.forEach(treeEntity -> {
            if (treeEntity.getParentId().equals(root)) {
                treeEntityArrayList.add(treeEntity);
            }
            treeEntities.forEach(childTreeEntity -> {
                if (childTreeEntity.getParentId().equals(treeEntity.getId())) {
                    if (treeEntity.getChildren() == null) {
                        treeEntity.setChildren(new ArrayList<>());
                    }
                    treeEntity.add(childTreeEntity);
                }
            });
        });
        return (List<T>) treeEntityArrayList;
    }

    public static <T> List<T> buildDeptTree(List<? extends AutoIncrementTreeEntity<T>> treeEntities, Integer root) {
        List<AutoIncrementTreeEntity<T>> treeEntityArrayList = new ArrayList<>();
        treeEntities.forEach(treeEntity -> {
            if (treeEntity.getParentId().equals(root)) {
                treeEntityArrayList.add(treeEntity);
            }
            treeEntities.forEach(childTreeEntity -> {
                if (childTreeEntity.getParentId().equals(treeEntity.getId())) {
                    if (treeEntity.getChildren() == null) {
                        treeEntity.setChildren(new ArrayList<>());
                    }
                    treeEntity.add(childTreeEntity);
                }
            });
        });
        return (List<T>) treeEntityArrayList;
    }


    /**
     * 不需要root也能够处理树，没有上级的元素会被置为顶级元素
     *
     * @param treeEntities
     * @param <T>
     * @return
     */
    public static <T> List<T> buildTreeWithOutRoot(List<? extends TreeEntity<T>> treeEntities) {
        List<TreeEntity<T>> treeEntityArrayList = new ArrayList<>();
        treeEntities.forEach(treeEntity -> {
            treeEntityArrayList.add(treeEntity);
            treeEntities.forEach(childTreeEntity -> {
                if (childTreeEntity.getParentId().equals(treeEntity.getId())) {
                    if (treeEntity.getChildren() == null) {
                        treeEntity.setChildren(new ArrayList<>());
                    }
                    treeEntity.add(childTreeEntity);
                }
            });
        });
        //处理完成后去重
        List<TreeEntity<T>> treeEntityArrayList2 = new ArrayList<>();
        treeEntityArrayList2.addAll(treeEntityArrayList);
        treeEntityArrayList.forEach(treeEntity -> {
            treeEntityArrayList.forEach(childTreeEntity -> {
                if (childTreeEntity.getParentId().equals(treeEntity.getId())) {
                    treeEntityArrayList2.remove(childTreeEntity);
                }
            });
        });
        return (List<T>) treeEntityArrayList2;
    }

    /**
     * 根据传过来的树工具实体类，转为实体类 T
     *
     * @param treeEntity
     * @param <T>
     * @return
     */
    public static <T> List<T> getTreeChildren(AutoIncrementTreeEntity<T> treeEntity) {
        List<AutoIncrementTreeEntity> children = treeEntity.getChildren();
        ArrayList<T> objects = new ArrayList<>();
        for (AutoIncrementTreeEntity child : children) {
            objects.add((T) child);
        }
        return objects;
    }

}
