import { Metadata } from "next";
import SignInComponent from "../_clients/signin-component";
import AppTitle from "@/components/widgets/app-title";

export const metadata: Metadata = {
  title: "Balances | Sign In",
  description: "Welcome member! Please sign in and continue your business.",
};

export default function SignInPage() {
    return (
        <div className="space-y-6">
            <AppTitle title="Sign In" subTitle="Welcome Back!" />
            <SignInComponent />
        </div>
    )
}