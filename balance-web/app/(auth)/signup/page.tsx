import AppTitle from "@/components/widgets/app-title";
import { Metadata } from "next";
import SignUpComponent from "../_clients/signup-coponent";

export const metadata: Metadata = {
  title: "Balances | Sign Up",
  description: "Create your company and manage your balances.",
};

export default function SignUpPage() {
    return (
        <div className="space-y-6">
            <AppTitle title="Sign Up" subTitle="Create account and manage your balance." />
            <SignUpComponent />
        </div>
    )
}