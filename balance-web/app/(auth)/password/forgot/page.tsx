import AppTitle from "@/components/widgets/app-title";
import { Metadata } from "next";
import ForgotPasswordComponent from "../../_clients/forgot-password-component";

export const metadata: Metadata = {
  title: "Balances | Forgot Password",
  description: "Please enter your email and request for reset password.",
};

export default function forgotPasswordPage() {

    return (
        <div className="space-y-6">
            <AppTitle title="Forgot Password" subTitle="Please enter your email and request for reset password." />
            <ForgotPasswordComponent />
        </div>
    )
}