import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { Redirect } from '@react-navigation/native'; // For @react-navigation/native


const index = () => {
  return (
    <Redirect href="/{tabs}/home"/>
  )
}

export default index

const styles = StyleSheet.create({})