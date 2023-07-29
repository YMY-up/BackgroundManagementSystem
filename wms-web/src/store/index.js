import vue from 'vue'
import Vuex from 'vuex'
import router,{resetRouter} from "../router";
vue.use(Vuex)


function addNewRoute(menuList) {
    /*菜单*/
    let routes = router.options.routes
    console.log(routes)
    console.log(menuList)
    routes.forEach(routeItem=>{
        if (routeItem.path=="/Index"){
            menuList.forEach(menu=>{
                let  childRoute = {
                    /*子路由*/
                        path: '/'+menu.menuclick,
                        name: menu.menuname,
                        meta: {
                            title: menu.menuname
                        },
                        component:()=>import('../components/'+menu.menucomponent)
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
    }
})