import vue from 'vue'
import Vuex from 'vuex'
import router,{resetRouter} from "../router"
import createPersistedState from 'vuex-persistedstate'
vue.use(Vuex)


function addNewRoute(menuList) {
    /*菜单*/
    let routes = router.options.routes
    console.log(routes)
    console.log(menuList)
    routes.forEach(routeItem=>{
        if (routeItem.path == "/Index"){
            menuList.forEach(menu=>{
                let  childRoute = {
                    /*子路由*/
                        path: '/'+menu.menuClick,
                        name: menu.menuName,
                        meta: {
                            title: menu.menuName
                        },
                        component:()=>import('../components/'+menu.menuComponent)
                }
                routeItem.children.push(childRoute)
            })
        }
    })
    resetRouter()
    router.addRoutes(routes)
}

export default new Vuex.Store( {
    state: {
        menu: []
    },
    mutations: {

        setMenu(state,menuList) {
            state.menu = menuList

            addNewRoute(menuList)
        }
    },
    getters: {
        getMenu(state) {
            return state.menu
        }
    },
    plugins: [createPersistedState()]       // 刷新 后持久化
})