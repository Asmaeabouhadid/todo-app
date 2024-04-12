import {stack} from "expo-router";

export default function Layout(){
    return(
        <Stack screenOptions={{HeaderShown:false}}>
            <Stack.Screen name="index"/>
        </Stack>
    ) 
}