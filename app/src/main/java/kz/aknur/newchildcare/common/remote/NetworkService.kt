package kz.aknur.newchildcare.common.remote



import kz.aknur.newchildcare.content.child.add.models.ChildAddRequest
import kz.aknur.newchildcare.content.child.childlist.models.ChildlistResponse
import kz.aknur.newchildcare.content.home.articles.ArticlesResponse
import kz.aknur.newchildcare.content.home.categories.models.MainCategoriesResponse
import kz.aknur.newchildcare.content.hospitals.models.HospitalsResponse
import kz.aknur.newchildcare.content.profile.models.ProfileResponse
import kz.aknur.newchildcare.signIn.models.AuthRequest
import kz.aknur.newchildcare.signIn.models.AuthResponse
import kz.aknur.newchildcare.signUp.firstPage.models.SignUpRequest
import kz.aknur.newchildcare.signUp.firstPage.models.SignUpResponse
import kz.aknur.newchildcare.signUp.secondPage.models.GetCitiesResponse
import kz.aknur.newchildcare.signUp.secondPage.models.GetOrganizationsResponse
import kz.aknur.newchildcare.signUp.secondPage.models.PersonalInformationRequest
import retrofit2.Response
import retrofit2.http.*


interface NetworkService {

    @POST(EndPoints.SIGN_IN)
    suspend fun signIn(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST(EndPoints.SIGN_UP)
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>

    @GET(EndPoints.GET_CITIES)
    suspend fun getCities(): Response<GetCitiesResponse>

    @GET(EndPoints.GET_ORGANIZATIONS)
    suspend fun getOrganizations(): Response<GetOrganizationsResponse>

    @PUT(EndPoints.POST_P_INFO)
    suspend fun sendPersonalInfo(
        @Header("Authorization") token: String,
        @Body personalInformationRequest: PersonalInformationRequest
    ): Response<Any?>

    @GET(EndPoints.GET_MAIN_CATEGORIES)
    suspend fun getMainCategories(): Response<MainCategoriesResponse>

    @POST(EndPoints.ADD_NEW_CHILD)
    suspend fun addNewChild(
        @Header("Authorization") token: String,
        @Body childAddRequest: ChildAddRequest
    ): Response<Any?>

    @GET(EndPoints.GET_PROFILE_INFO)
    suspend fun getProfileInfo(
        @Header("Authorization") token: String
    ): Response<ProfileResponse>

    @GET(EndPoints.GET_ALL_ORG)
    suspend fun getHospitals(): Response<HospitalsResponse>

    @GET(EndPoints.GET_MY_CHILD_LIST)
    suspend fun getMyChildList(
        @Header("Authorization") token: String
    ): Response<ChildlistResponse>

    @GET(EndPoints.GET_ARTICLES_BY_CATEGORY)
    suspend fun getArticles(
        @Path("categoryId") categoryId: String,
        @Header("Authorization") token: String
    ): Response<ArticlesResponse>

}