import {Tabs} from "expo-router";
import { FontAwesome } from '@expo/vector-icons';
import { AntDesign } from '@expo/vector-icons';
import { MaterialCommunityIcons } from '@expo/vector-icons';

export default function layout(){

    return(
        <Tabs>
            <Tabs.Screen name="home" options={{
                tabBarLabel:"Home",
            tabBarLabelStyle:{color:"#7CB9E8"},
            headerShadowVisible:false,
            tabBarIcon:({focused}) =>
            focused?(
            <FontAwesome name="tasks" size={24} color="#7CB9E8" />
              ):(
                <FontAwesome name="tasks" size={24} color="black" />
              )
            }}/>
               <Tabs.Screen name="calendar" options={{
                tabBarLabel:"calendar",
            tabBarLabelStyle:{color:"#7CB9E8"},
            headerShadowVisible:false,
            tabBarIcon:({focused}) =>
            focused?(
                <AntDesign name="calendar" size={24} color="#7CB9E8" />
              ):(
                <AntDesign name="calendar" size={24} color="black" />
              )
            }}/>
               <Tabs.Screen name="profile" options={{
                tabBarLabel:"profile",
            tabBarLabelStyle:{color:"#7CB9E8"},
            headerShadowVisible:false,
            tabBarIcon:({focused}) =>
            focused?(
                <MaterialCommunityIcons name="account-details" size={24} color="#7CB9E8" />
              ):(
                <MaterialCommunityIcons name="account-details" size={24} color="black" />
              )
            }}/>
        </Tabs>
    )
}
