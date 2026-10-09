import { ChevronRight } from "lucide-react";
import Link from "next/link";

export default function DetailsLink({route} : {route : string}) {
    return (
        <Link href={route}>
            <ChevronRight size={20} />
        </Link>
    )
}