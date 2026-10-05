import AppTitle from "@/components/widgets/app-title";
import { Metadata } from "next";
import ResetPasswordComponent from "../../_clients/reset-password-component";

export const metadata: Metadata = {
  title: "Balances | Reset Password",
  description: "Please check your email and reset password with secret code.",
};

export default function ResetPasswordPage() {
    return (
        <div className="space-y-6">
            <AppTitle title="Reset Password" subTitle="Please check your email and reset password with secret code." />
            <ResetPasswordComponent />
        </div>
    )
}