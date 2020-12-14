package kz.aknur.newchildcare.common.remote

object EndPoints {
    const val SIGN_IN = "auth/login" //Авторизация
    const val SIGN_UP = "auth/signup" //Регистрация
    const val GET_CITIES = "cities" //Список городов
    const val GET_ORGANIZATIONS = "organizations" //Список больниц
    const val POST_P_INFO = "users/dto"
    const val GET_MAIN_CATEGORIES = "categories/main"
    const val ADD_NEW_CHILD = "children"
    const val GET_PROFILE_INFO = "profile"
    const val GET_ALL_ORG = "organizations"
    const val GET_MY_CHILD_LIST = "children/my"
    const val GET_ARTICLES_BY_CATEGORY = "articles/catId/{categoryId}"
}