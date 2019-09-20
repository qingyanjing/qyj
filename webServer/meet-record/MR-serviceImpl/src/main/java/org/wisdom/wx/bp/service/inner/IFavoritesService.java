package org.wisdom.wx.bp.service.inner;

import org.wisdom.wx.bp.model.crud.Favorites;
import org.wisdom.wx.bp.model.crud.FavoritesExample;

import java.util.List;

public interface IFavoritesService {
    Boolean insertFavorites(Favorites favorites);
    boolean cancelFavorites(String favoritesId);
    List<Favorites> selectFavorites(FavoritesExample favorites);
}
