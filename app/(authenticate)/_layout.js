import { stack } from "expo-router";

export default function Layout() {
  return (
    <Stack>
      <Stack.Screen name="login" screenOptions={{ HeaderShown: false }} />
      <Stack.Screen name="register" screenOptions={{ HeaderShown: false }} />
    </Stack>
  );
}
