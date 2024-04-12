import { Pressable, ScrollView, StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { AntDesign } from '@expo/vector-icons';

const index = () => {
  const todos=[];
  return (
  <>
  
    <View style={{marginHorizontal:10,
      marginVertical:10,
      flexDirection:'row',alignItems:"center",
      gap:12}}>

      <Pressable style={{backgroundColor:"red",
      paddingHorizontal:10,
      paddingVertical:6,
      borderRadius:25,
      alignItems:"center",
      justifyContent:"center"}}>
        <text style={{color:"white",textAlign:"center"}}>
          All
        </text>
      </Pressable>
      <Pressable style={{backgroundColor:"red",
      paddingHorizontal:10,
      paddingVertical:6,
      borderRadius:25,
      alignItems:"center",
      justifyContent:"center"}}>
        <text style={{color:"white",textAlign:"center"}}>
          Work 
        </text>
      </Pressable>
      <Pressable style={{backgroundColor:"red",
      paddingHorizontal:10,
      paddingVertical:6,
      borderRadius:25,
      alignItems:"center",
      justifyContent:"center",
      marginRight:"auto"
      }}>
        <text style={{color:"white",textAlign:"center"}}>
          Personal
        </text>
      </Pressable>
      <Pressable>
      <AntDesign name="pluscircle" size={30} color="#007FFF" />
      </Pressable>
    </View>
    <ScrollView style={{flex:1,backgroundColor:"white"}}>
       <View style={{padding:10}}>
        {todos?.length > 0?(
<View></View>
        ):(
          <View style={{flex:1,
          justifyContent:"center",
          alignItems:"center",
          marginTop:130,
          marginLeft:"auto",
          marginRight:"auto"}}>
            <Image
            style={{width:200,
              height:200
              ,resizeMode:"contain"}}
            source ={{
              uri:"https://cdn-icons.png.flaticon.com/128/2387/2387679.png",
            }}
            />
<Text style ={{fontSize:16,
  marginTop:15,
  fontWeight:"600",
  textAlign:"center"}}>
     No Tasks For Today ! add a task</Text>
<Pressable style={{marginTop:15}}>
      <AntDesign name="pluscircle"
       size={30} color="#007FFF" />
      </Pressable>
          </View>
        )}
        </View>  
    </ScrollView>
    </> 
  )
}

export default index

const styles = StyleSheet.create({})