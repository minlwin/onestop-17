import AppTitle from "@/components/widgets/app-title";
import { Metadata } from "next";
import ActivationComponent from "../_clients/activation-component";

export const metadata: Metadata = {
  title: "Balances | Activation",
  description: "Please activate your account.",
};

export default function ActivationPage() {

    return (
        <div className="space-y-6">
            <AppTitle title="Activate Account" subTitle="Please activate your account with secret code and passowrd." />
            <ActivationComponent />
        </div>
    )
}